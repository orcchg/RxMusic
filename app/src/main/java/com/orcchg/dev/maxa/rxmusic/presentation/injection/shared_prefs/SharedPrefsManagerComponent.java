package com.orcchg.dev.maxa.rxmusic.presentation.injection.shared_prefs;

import com.orcchg.dev.maxa.rxmusic.presentation.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefsManagerModule.class})
public interface SharedPrefsManagerComponent {

    SharedPrefsManager sharedPrefsManager();
}
