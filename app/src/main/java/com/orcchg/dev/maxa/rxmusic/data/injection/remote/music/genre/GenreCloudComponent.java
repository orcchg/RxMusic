package com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.CloudComponent;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre.ServerGenreRestAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {GenreCloudModule.class})
public interface GenreCloudComponent extends CloudComponent {

    ServerGenreRestAdapter restAdapter();
}
