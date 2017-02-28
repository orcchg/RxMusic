package com.orcchg.dev.maxa.rxmusic.presentation.ui.base;

import android.support.v7.widget.RecyclerView;

public interface MvpListView extends MvpView {
    RecyclerView getListView(int tag);
}
