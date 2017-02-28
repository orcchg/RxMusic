package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.orcchg.dev.maxa.rxmusic.domain.interactor.music.GetArtists;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.music.GetTotalArtists;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistListItemVO;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.mapper.ArtistListItemMapper;

import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import rx.Subscriber;
import timber.log.Timber;

public class ArtistListPresenter extends BasePresenter<ArtistListContract.View>
        implements ArtistListContract.Preseneter {
    private static final int LIMIT_PER_REQUEST = 20;
    private static final int LOAD_MORE_THRESHOLD = 1;

    private ArtistListAdapter artistsAdapter;

    private final GetArtists getArtistsUseCase;
    private final GetTotalArtists getTotalArtistsUseCase;

    // --------------------------------------------------------------------------------------------
    private static class Memento {
        static final String BUNDLE_KEY_CURRENT_SIZE = "bundle_key_current_size";
        static final String BUNDLE_KEY_CURRENT_OFFSET = "bundle_key_current_offset";
        static final String BUNDLE_KEY_TOTAL_ARTISTS = "bundle_key_total_artists";

        int currentSize = 0;
        int currentOffset = 0;
        int totalArtists = 0;

        void toBundle(Bundle outState) {
            outState.putInt(BUNDLE_KEY_CURRENT_SIZE, currentSize);
            outState.putInt(BUNDLE_KEY_CURRENT_OFFSET, currentOffset);
            outState.putInt(BUNDLE_KEY_TOTAL_ARTISTS, totalArtists);
        }

        static Memento fromBundle(Bundle savedInstanceState) {
            Memento memento = new Memento();
            memento.currentSize = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_SIZE);
            memento.currentOffset = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_OFFSET);
            memento.totalArtists = savedInstanceState.getInt(BUNDLE_KEY_TOTAL_ARTISTS);
            return memento;
        }
    }

    private Memento memento = new Memento();

    // --------------------------------------------------------------------------------------------
    @Inject
    ArtistListPresenter(GetArtists getArtistsUseCase, GetTotalArtists getTotalArtistsUseCase) {
        this.artistsAdapter = new ArtistListAdapter((view, artistId) -> openArtistDetails(artistId));
        this.artistsAdapter.setOnErrorClickListener((view) -> retryLoadMore());
        this.getArtistsUseCase = getArtistsUseCase;
        this.getTotalArtistsUseCase = getTotalArtistsUseCase;
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            memento = Memento.fromBundle(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isViewAttached()) {
            RecyclerView list = getView().getListView();
            if (list.getAdapter() == null) {
                list.setAdapter(artistsAdapter);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        memento.toBundle(outState);
    }

    @Override
    public void onDestroy() {
        getArtistsUseCase.unsubscribe();
        getTotalArtistsUseCase.unsubscribe();
        super.onDestroy();
    }

    /* Contract */
    // --------------------------------------------------------------------------------------------
    @DebugLog @Override
    public void retry() {
        memento.currentOffset = 0;
        memento.currentSize = 0;
        memento.totalArtists = 0;
        freshStart();
    }

    @DebugLog @Override
    public void onScroll(int itemsLeftToEnd) {
        if (isThereMore() && itemsLeftToEnd <= LOAD_MORE_THRESHOLD) {
            memento.currentOffset += LIMIT_PER_REQUEST;
            loadArtists(LIMIT_PER_REQUEST, memento.currentOffset, /* memento.genres */ null);
        }
    }

    /* Callback */
    // --------------------------------------------------------------------------------------------

    /* Internal */
    // --------------------------------------------------------------------------------------------
    @Override
    protected void freshStart() {
        GetTotalArtists.Parameters parameters = new GetTotalArtists.Parameters.Builder()
                .setGenres(/* memento.genres */ null)
                .build();
        getTotalArtistsUseCase.setParameters(parameters);
        getTotalArtistsUseCase.execute(new Subscriber<TotalValue>() {
            @Override
            public void onCompleted() {
                loadArtists(LIMIT_PER_REQUEST, 0, /* memento.genres */ null);
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) getView().showError();
            }

            @Override
            public void onNext(TotalValue total) {
                Timber.i("Total artists: %s", total.value());
                memento.totalArtists = total.value();
            }
        });
    }

    @Override
    protected void onRestoreState() {
        memento = Memento.fromBundle(savedInstanceState);
        int limit = memento.currentSize;
        memento.currentSize = 0;
        artistsAdapter.clear();
        loadArtists(limit, 0, /* memento.genres */ null);
    }

    @DebugLog
    private void retryLoadMore() {
        artistsAdapter.onError(false);  // show loading more
        loadArtists(LIMIT_PER_REQUEST, memento.currentOffset, /* memento.genres */ null);
    }

    @DebugLog
    private void loadArtists() {
        loadArtists(-1, 0);
    }

    @DebugLog
    private void loadArtists(int limit, int offset) {
        loadArtists(limit, offset, null);
    }

    @DebugLog
    private void loadArtists(@Nullable List<String> genres) {
        loadArtists(-1, 0, genres);
    }

    @DebugLog
    private void loadArtists(int limit, int offset, @Nullable List<String> genres) {
//        memento.genres = genres;
        if (isViewAttached()) {
            if (memento.totalArtists <= 0) {
                getView().showLoading();
            }
        }
        GetArtists.Parameters parameters = new GetArtists.Parameters.Builder()
                .setLimit(limit)
                .setOffset(offset)
                .setGenres(genres)
                .build();
        getArtistsUseCase.setParameters(parameters);
        getArtistsUseCase.execute(new Subscriber<List<SmallArtist>>() {
            @Override
            public void onCompleted() {
                // no-op
            }

            @Override
            public void onError(Throwable e) {
                if (memento.currentSize <= 0) {
                    if (isViewAttached()) getView().showError();
                } else {
                    artistsAdapter.onError(true);
                }
            }

            @Override
            public void onNext(List<SmallArtist> artists) {
                memento.currentSize += artists.size();
                ArtistListItemMapper mapper = new ArtistListItemMapper();
                List<ArtistListItemVO> vos = mapper.map(artists);
                artistsAdapter.populate(vos, isThereMore());
                if (isViewAttached()) getView().showArtists(vos);
            }
        });
    }

    private void openArtistDetails(long artistId) {
        if (isViewAttached()) {
            getView().openArtistDetails(artistId);
        }
    }

    @DebugLog
    private boolean isThereMore() {
        return memento.totalArtists > memento.currentSize + memento.currentOffset;
    }
}
