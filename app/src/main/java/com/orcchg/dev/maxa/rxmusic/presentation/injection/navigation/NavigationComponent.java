package com.orcchg.dev.maxa.rxmusic.presentation.injection.navigation;

import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.navigation.Navigator;
import com.orcchg.dev.maxa.rxmusic.presentation.navigation.NavigatorHolder;

import dagger.Component;

@PerActivity
@Component(modules = {NavigationModule.class})
public interface NavigationComponent {

    void inject(NavigatorHolder holder);

    Navigator navigator();
}
