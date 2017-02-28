package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BaseListFragment;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;

public class SimpleBaseListFragment extends BaseListFragment<StubMvpView, BasePresenter<StubMvpView>>
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

    @Override
    protected void onScrollTop() {
        // override in subclass
    }
}
