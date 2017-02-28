package com.orcchg.dev.maxa.rxmusic.presentation.ui;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import hugo.weaving.DebugLog;
import timber.log.Timber;

@Singleton
public class SharedPrefsManager {
    private static final String SHARED_PREFS_FILE_NAME = "rxmusic_shared_preferences";

    private final SharedPreferences sharedPreferences;

    @DebugLog @Inject
    public SharedPrefsManager(Context context) {
        Timber.d("SharedPrefsManager ctor");
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    /* Read & Write shared preferences */
    // --------------------------------------------------------------------------------------------
}
