package com.orcchg.dev.maxa.rxmusic.presentation.injection.navigation;

import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.navigation.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {

    @Provides @PerActivity
    Navigator provideNavigator() {
        return new Navigator();
    }
}
