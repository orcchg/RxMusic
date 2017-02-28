package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.MvpPresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.MvpView;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistDetailsVO;

public interface ArtistDetailsContract {
    interface View extends MvpView {
        void setGrade(int grade);
        void showArtist(ArtistDetailsVO artist);
        void showError();
    }

    interface Preseneter extends MvpPresenter<View> {
    }
}
