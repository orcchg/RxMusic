package com.orcchg.dev.maxa.rxmusic.presentation.executor;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Singleton
public class UiThread implements PostExecuteScheduler {

    @Inject
    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
