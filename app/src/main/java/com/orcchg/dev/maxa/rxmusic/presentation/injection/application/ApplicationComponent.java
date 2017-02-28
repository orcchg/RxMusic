package com.orcchg.dev.maxa.rxmusic.presentation.injection.application;

import android.content.Context;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist.ArtistRepositoryModule;
import com.orcchg.dev.maxa.rxmusic.domain.injection.music.genre.GenreRepositoryModule;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.GenreRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ArtistRepositoryModule.class, GenreRepositoryModule.class})
public interface ApplicationComponent {

    Context context();
    ThreadExecutor threadExecutor();
    PostExecuteScheduler postExecuteScheduler();

    ArtistRepository artistRepository();
    GenreRepository genreRepository();
}
