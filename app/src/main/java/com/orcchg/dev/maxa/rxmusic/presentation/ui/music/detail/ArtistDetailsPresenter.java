package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail;

import com.orcchg.dev.maxa.rxmusic.domain.interactor.music.GetArtistDetails;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.util.music.ArtistUtils;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistDetailsVO;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.mapper.ArtistDetailsMapper;

import javax.inject.Inject;

import rx.Subscriber;

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailsContract.View>
        implements ArtistDetailsContract.Preseneter {

    private final GetArtistDetails getArtistDetailsUseCase;

    @Inject
    ArtistDetailsPresenter(GetArtistDetails getArtistDetailsUseCase) {
        this.getArtistDetailsUseCase = getArtistDetailsUseCase;
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    @Override
    public void onDestroy() {
        getArtistDetailsUseCase.unsubscribe();
        super.onDestroy();
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    @Override
    protected void freshStart() {
        getArtistDetailsUseCase.execute(new Subscriber<Artist>() {
            @Override
            public void onCompleted() {
                // no-op
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) getView().showError();
            }

            @Override
            public void onNext(Artist artist) {
                ArtistDetailsMapper mapper = new ArtistDetailsMapper();
                ArtistDetailsVO artistVO = mapper.map(artist);
                int grade = ArtistUtils.calculateGrade(artist);
                if (isViewAttached()) {
                    getView().showArtist(artistVO);
                    getView().setGrade(grade);
                }
            }
        });
    }

    @Override
    protected void onRestoreState() {
        freshStart();  // nothing to be restored
    }
}
