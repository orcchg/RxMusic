package com.orcchg.dev.maxa.rxmusic.domain.injection.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.injection.remote.music.genre.GenreCloudModule;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.GenreToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.GenreToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.GenreMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.TotalValueMapper;
import com.orcchg.dev.maxa.rxmusic.data.source.local.music.genre.GenreDatabase;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre.GenreCloud;
import com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre.ServerGenreRestAdapter;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre.GenreDataSource;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre.GenreRepositoryImpl;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.GenreRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {GenreCloudModule.class})
public class GenreRepositoryModule {

    @Named("genreSource") @Provides @Singleton
    GenreDataSource provideGenreCloudSource(ServerGenreRestAdapter restAdapter, GenreMapper genreMapper,
            TotalValueMapper totalValueMapper) {
        return new GenreCloud(restAdapter, genreMapper, totalValueMapper);
    }

    @Named("genreDatabase") @Provides @Singleton
    GenreDataSource provideGenreLocalSource(GenreToDboMapper mapper, GenreToDboPopulator populator) {
        return new GenreDatabase(mapper, populator);
    }

    @Provides @Singleton
    GenreRepository provideGenreRepository(GenreRepositoryImpl repository) {
        return repository;
    }
}
