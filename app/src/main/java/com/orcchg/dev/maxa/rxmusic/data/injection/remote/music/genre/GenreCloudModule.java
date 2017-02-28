package com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.CloudModule;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre.ServerGenreRestAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {CloudModule.class})
public class GenreCloudModule {

    @Provides @Singleton
    public ServerGenreRestAdapter provideServerGenreRestAdapter(Retrofit.Builder retrofit) {
        return retrofit.baseUrl(ServerGenreRestAdapter.ENDPOINT).build()
                .create(ServerGenreRestAdapter.class);
    }
}
