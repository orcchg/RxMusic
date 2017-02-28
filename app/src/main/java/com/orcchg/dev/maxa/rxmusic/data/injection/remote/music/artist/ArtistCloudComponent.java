package com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.artist;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.CloudComponent;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist.ServerArtistRestAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ArtistCloudModule.class})
public interface ArtistCloudComponent extends CloudComponent {

    ServerArtistRestAdapter restAdapter();
}
