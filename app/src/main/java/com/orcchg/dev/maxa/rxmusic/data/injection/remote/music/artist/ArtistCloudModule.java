package com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.artist;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.CloudModule;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist.ServerArtistRestAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {CloudModule.class})
public class ArtistCloudModule {

    @Provides @Singleton
    public ServerArtistRestAdapter provideServerArtistRestAdapter(Retrofit.Builder retrofit) {
        return retrofit.baseUrl(ServerArtistRestAdapter.ENDPOINT).build()
                .create(ServerArtistRestAdapter.class);
    }
}
