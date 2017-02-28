package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orcchg.dev.maxa.rxmusic.R;
//import com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist.DaggerArtistRepositoryComponent;
import com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist.ArtistRepositoryComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BaseActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.common.view.misc.GridItemDecorator;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.common.view.misc.ViewUtility;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.injection.ArtistListComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.injection.DaggerArtistListComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistListItemVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class ArtistListActivity extends BaseActivity<ArtistListContract.View, ArtistListContract.Preseneter>
        implements ArtistListContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rl_toolbar_dropshadow) View dropshadowView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_items) RecyclerView artistsList;
    @BindView(R.id.empty_view) View emptyView;
    @BindView(R.id.loading_view) View loadingView;
    @BindView(R.id.error_view) View errorView;
    @OnClick(R.id.btn_retry)
    public void onRetryClick() {
        presenter.retry();
    }

    private ArtistListComponent component;
    private LinearLayoutManager layoutManager;

    private int lastVisible = -1;

    @NonNull @Override
    protected ArtistListContract.Preseneter createPresenter() {
        return component.presenter();
    }

    @Override
    protected void injectDependencies() {
//        ArtistRepositoryComponent artistRepositoryComponent = DaggerArtistRepositoryComponent.create();
        component = DaggerArtistListComponent.builder()
                .applicationComponent(getApplicationComponent())
//                .artistRepositoryComponent(artistRepositoryComponent)
                .build();
    }

    public static Intent getCallingIntent(@NonNull Context context) {
        return new Intent(context, ArtistListActivity.class);
    }

    // --------------------------------------------------------------------------------------------
    private static class Memento {
        private static final String BUNDLE_KEY_LM_STATE = "bundle_key_lm_state";

        private Parcelable layoutManagerState;

        void toBundle(@NonNull Bundle outState) {
            outState.putParcelable(BUNDLE_KEY_LM_STATE, layoutManagerState);
        }

        static Memento fromBundle(@NonNull Bundle savedInstanceState) {
            Memento memento = new Memento();
            memento.layoutManagerState = savedInstanceState.getParcelable(BUNDLE_KEY_LM_STATE);
            return memento;
        }
    }

    Memento memento = new Memento();

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        initView();
        initToolbar();

        if (savedInstanceState != null) {
            memento = Memento.fromBundle(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isStateRestored() && memento.layoutManagerState != null) {
            Timber.i("Restored state of layout manager");
            layoutManager.onRestoreInstanceState(memento.layoutManagerState);
        }
        artistsList.setLayoutManager(layoutManager);
    }

    @DebugLog @Override
    public void onSaveInstanceState(Bundle outState) {
        memento.layoutManagerState = layoutManager.onSaveInstanceState();
        memento.toBundle(outState);
        super.onSaveInstanceState(outState);
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private void initView() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.retry());

        if (ViewUtility.isLargeScreen(this)) {
            layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.grid_span));
            artistsList.addItemDecoration(new GridItemDecorator(this, (R.dimen.grid_card_spacing)));
        } else {
            layoutManager = new LinearLayoutManager(this);
        }

        artistsList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                processListScroll(recyclerView, dx, dy);
            }
        });
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.str_musicians_list);
        toolbar.setNavigationOnClickListener((view) -> finish());
    }

    /* Contract */
    // --------------------------------------------------------------------------------------------
    @Override
    public RecyclerView getListView() {
        return artistsList;
    }

    @Override
    public void openArtistDetails(long artistId) {
        navigationComponent.navigator().openDetailsScreen(this, artistId);
    }

    @Override
    public void showArtists(List<ArtistListItemVO> artists) {
        swipeRefreshLayout.setRefreshing(false);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);

        if (artists == null || artists.isEmpty()) {
            artistsList.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
            artistsList.setVisibility(View.VISIBLE);
        }
        showShadow(true);
    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
        artistsList.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        showShadow(true);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(false);
        artistsList.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        showShadow(false);  // don't overlap with progress bar
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    void processListScroll(RecyclerView recyclerView, int dx, int dy) {
        if (dy <= 0) {
            return;  // skip scroll up
        }

        int last = layoutManager.findLastVisibleItemPosition();
        if (lastVisible == last) {
            return;  // skip scroll due to layout
        }

        lastVisible = last;
        int total = layoutManager.getItemCount();
        presenter.onScroll(total - last);
    }

    public void showShadow(boolean show) {
        dropshadowView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}
