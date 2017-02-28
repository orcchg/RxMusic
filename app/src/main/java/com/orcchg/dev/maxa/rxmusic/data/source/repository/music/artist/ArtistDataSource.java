package com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist;

import android.support.annotation.Nullable;

import com.orcchg.dev.maxa.rxmusic.data.source.repository.DataSource;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import rx.Observable;

/**
 * Source of {@link Artist} data.
 */
public interface ArtistDataSource extends DataSource {

    public Observable<List<SmallArtist>> artists();
    public Observable<List<SmallArtist>> artists(int limit, int offset);
    public Observable<List<SmallArtist>> artists(@Nullable List<String> genres);
    public Observable<List<SmallArtist>> artists(int limit, int offset, @Nullable List<String> genres);

    public Observable<Artist> artist(long artistId);

    public Observable<TotalValue> total();
    public Observable<TotalValue> total(@Nullable List<String> genres);
}
