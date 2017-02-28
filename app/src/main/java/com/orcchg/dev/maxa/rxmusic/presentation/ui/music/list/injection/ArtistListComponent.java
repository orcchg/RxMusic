package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.injection;

import com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist.ArtistRepositoryComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.ApplicationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.ArtistListPresenter;

import dagger.Component;

@PerActivity
@Component(modules = {ArtistListModule.class},
           dependencies = {ApplicationComponent.class, ArtistRepositoryComponent.class})
public interface ArtistListComponent {

    ArtistListPresenter presenter();
}
