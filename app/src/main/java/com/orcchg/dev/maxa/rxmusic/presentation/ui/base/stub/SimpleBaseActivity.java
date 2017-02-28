package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub;

import android.support.annotation.NonNull;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BaseActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;

public abstract class SimpleBaseActivity extends BaseActivity<StubMvpView, BasePresenter<StubMvpView>>
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
