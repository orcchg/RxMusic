package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub;

import android.support.annotation.NonNull;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BaseDialogFragment;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.BasePresenter;

public class SimpleBaseDialogFragment extends BaseDialogFragment<StubMvpView, BasePresenter<StubMvpView>> {

    @NonNull @Override
    protected BasePresenter<StubMvpView> createPresenter() {
        return new SimpleBasePresenter();
    }

    @Override
    protected void injectDependencies() {
        // empty
    }
}
