package com.orcchg.dev.maxa.rxmusic.presentation.injection.permission;

import android.content.Context;
import com.orcchg.dev.maxa.rxmusic.presentation.PermissionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PermissionManagerModule {

    private final Context context;

    public PermissionManagerModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    PermissionManager providePermissionManager() {
        return new PermissionManager(context);
    }
}
