package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail.injection;

import com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist.ArtistRepositoryComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.ApplicationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail.ArtistDetailsPresenter;

import dagger.Component;

@PerActivity
@Component(modules = {ArtistDetailsModule.class},
           dependencies = {ApplicationComponent.class, ArtistRepositoryComponent.class})
public interface ArtistDetailsComponent {

    /**
     * Template for dagger-generated factory method to provide
     * an instance of {@link ArtistDetailsPresenter} class for where this
     * {@link ArtistDetailsComponent} injects to.
     */
    ArtistDetailsPresenter presenter();
}
