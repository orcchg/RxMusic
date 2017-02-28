package com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre;

import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.GenreRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class GenreRepositoryImpl implements GenreRepository {

    private final GenreDataSource cloudSource;
    private final GenreDataSource localSource;

    @Inject
    public GenreRepositoryImpl(@Named("genreSource") GenreDataSource cloudSource,
                               @Named("genreDatabase") GenreDataSource localSource) {
        this.cloudSource = cloudSource;
        this.localSource = localSource;
    }

    @Override
    public Observable<List<Genre>> genres() {
        return cloudSource.genres();
    }

    @Override
    public Observable<TotalValue> total() {
        return cloudSource.total();
    }
}
