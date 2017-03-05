package com.orcchg.dev.maxa.rxmusic.presentation.injection.shared_prefs;

import android.content.Context;

import com.orcchg.dev.maxa.rxmusic.presentation.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsManagerModule {

    private final Context context;

    public SharedPrefsManagerModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    SharedPrefsManager provideSharedPrefsManager() {
        return new SharedPrefsManager(context);
    }
}
