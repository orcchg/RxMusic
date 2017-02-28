package com.orcchg.dev.maxa.rxmusic.presentation.ui.common.screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub.PassiveView;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub.SimpleBasePresenter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub.StubMvpView;

public abstract class SimpleCollectionFragment extends CollectionFragment<StubMvpView, BasePresenter<StubMvpView>>
        implements PassiveView {

    @NonNull @Override
    protected BasePresenter<StubMvpView> createPresenter() {
        return new SimpleBasePresenter();
    }

    @Override
    protected void injectDependencies() {
        // empty
    }

    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void onScroll(int itemsLeftToEnd) {
        // override in subclass
    }
}
