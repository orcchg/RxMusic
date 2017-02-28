package com.orcchg.dev.maxa.rxmusic.presentation.ui.base;

import android.app.Service;

import com.orcchg.dev.maxa.rxmusic.domain.util.DebugSake;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.util.MainLooperSpy;

import timber.log.Timber;

public abstract class BaseService extends Service {

    protected final @DebugSake MainLooperSpy mainLooperSpy = new MainLooperSpy();

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onCreate(service=%s)", hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onDestroy(service=%s)", hashCode());
    }
}
