package com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist;

import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Concrete implementation of {@link ArtistRepository}. This repository rules where to fetch data
 * from. It checks local storage and in case of cache-miss requests cloud source for data.
 * It also manages to cache data locally.
 */
@Singleton
public class ArtistRepositoryImpl implements ArtistRepository {

    private final ArtistDataSource cloudSource;
    private final ArtistDataSource localSource;

    @Inject
    public ArtistRepositoryImpl(@Named("artistCloud") ArtistDataSource cloudSource,
                                @Named("artistDatabase") ArtistDataSource localSource) {
        this.cloudSource = cloudSource;
        this.localSource = localSource;
    }

    @Override
    public Observable<List<SmallArtist>> artists() {
        return cloudSource.artists();
    }

    @Override
    public Observable<List<SmallArtist>> artists(int limit, int offset, List<String> genres) {
        return cloudSource.artists(limit, offset, genres);
    }

    @Override
    public Observable<Artist> artist(long artistId) {
        return cloudSource.artist(artistId);
    }

    @Override
    public Observable<TotalValue> total() {
        return cloudSource.total();
    }

    @Override
    public Observable<TotalValue> total(List<String> genres) {
        return cloudSource.total(genres);
    }
}
