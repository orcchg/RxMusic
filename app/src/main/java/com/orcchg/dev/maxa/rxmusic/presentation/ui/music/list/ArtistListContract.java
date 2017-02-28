package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list;

import android.support.v7.widget.RecyclerView;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.MvpPresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.MvpView;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistListItemVO;

import java.util.List;

public interface ArtistListContract {
    interface View extends MvpView {
        RecyclerView getListView();

        void openArtistDetails(long artistId);

        void showArtists(List<ArtistListItemVO> artists);
        void showError();
        void showLoading();
    }

    interface Preseneter extends MvpPresenter<View> {
        void retry();
        void onScroll(int itemsLeftToEnd);
    }
}
