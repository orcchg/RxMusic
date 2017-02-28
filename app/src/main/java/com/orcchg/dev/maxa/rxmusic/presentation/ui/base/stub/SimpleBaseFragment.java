package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub;

import android.support.annotation.NonNull;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BaseFragment;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;

public abstract class SimpleBaseFragment extends BaseFragment<StubMvpView, BasePresenter<StubMvpView>>
        implements PassiveView {

    @NonNull @Override
    protected BasePresenter<StubMvpView> createPresenter() {
        return new SimpleBasePresenter();
    }

    @Override
    protected void injectDependencies() {
        // empty
    }
}
