package com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.ArtistMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.SmallArtistMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.TotalValueMapper;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist.ArtistDataSource;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hugo.weaving.DebugLog;
import rx.Observable;

@Singleton
public class ArtistCloud implements ArtistDataSource {

    private final ServerArtistRestAdapter restAdapter;

    private final ArtistMapper artistMapper;
    private final SmallArtistMapper smallArtistMapper;
    private final TotalValueMapper totalValueMapper;

    @Inject
    public ArtistCloud(ServerArtistRestAdapter restAdapter, ArtistMapper artistMapper,
                       SmallArtistMapper smallArtistMapper, TotalValueMapper totalValueMapper) {
        this.restAdapter = restAdapter;
        this.artistMapper = artistMapper;
        this.smallArtistMapper = smallArtistMapper;
        this.totalValueMapper = totalValueMapper;
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists() {
        return artists(-1, 0);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(int limit, int offset) {
        return artists(limit, offset, null);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(@Nullable List<String> genres) {
        return artists(-1, 0, genres);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(int limit, int offset, @Nullable List<String> genres) {
        Integer Limit = limit == -1 ? null : limit;
        Integer Offset = offset == 0 ? null : offset;
        String genresQuery = genres == null || genres.isEmpty() ? null : TextUtils.join(",", genres);
        return restAdapter.artists(Limit, Offset, genresQuery).map(smallArtistMapper::map);
    }

    @DebugLog @Override
    public Observable<Artist> artist(long artistId) {
        return restAdapter.artist(artistId).map(artistMapper::map);
    }

    @DebugLog @Override
    public Observable<TotalValue> total() {
        return restAdapter.total(null).map(totalValueMapper::map);
    }

    @DebugLog @Override
    public Observable<TotalValue> total(@Nullable List<String> genres) {
        String genresQuery = genres == null || genres.isEmpty() ? null : TextUtils.join(",", genres);
        return restAdapter.total(genresQuery).map(totalValueMapper::map);
    }
}
