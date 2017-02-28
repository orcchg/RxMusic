package com.orcchg.dev.maxa.rxmusic.domain.injection.music.artist;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.artist.ArtistCloudModule;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.ArtistToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.SmallArtistToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.ArtistToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.ArtistMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.SmallArtistMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.TotalValueMapper;
import com.orcchg.dev.maxa.rxmusic.data.source.local.music.artist.ArtistDatabase;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist.ArtistCloud;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist.ServerArtistRestAdapter;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist.ArtistDataSource;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist.ArtistRepositoryImpl;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ArtistCloudModule.class)
public class ArtistRepositoryModule {

    @Named("artistCloud") @Provides @Singleton
    ArtistDataSource provideArtistCloudSource(ServerArtistRestAdapter restAdapter,
            ArtistMapper artistMapper, SmallArtistMapper smallArtistMapper, TotalValueMapper totalValueMapper) {
        return new ArtistCloud(restAdapter, artistMapper, smallArtistMapper, totalValueMapper);
    }

    @Named("artistDatabase") @Provides @Singleton
    ArtistDataSource provideArtistLocalSource(ArtistToDboMapper artistMapper,
            SmallArtistToDboMapper smallArtistMapper, ArtistToDboPopulator populator) {
        return new ArtistDatabase(artistMapper, smallArtistMapper, populator);
    }

    @Provides @Singleton
    ArtistRepository provideArtistRepository(ArtistRepositoryImpl repository) {
        return repository;
    }
}
