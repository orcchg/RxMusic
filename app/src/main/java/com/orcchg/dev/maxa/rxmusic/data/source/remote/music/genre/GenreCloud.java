package com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.GenreMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper.TotalValueMapper;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre.GenreDataSource;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hugo.weaving.DebugLog;
import rx.Observable;

@Singleton
public class GenreCloud implements GenreDataSource {

    private final ServerGenreRestAdapter restAdapter;

    private final GenreMapper genreMapper;
    private final TotalValueMapper totalValueMapper;

    @Inject
    public GenreCloud(ServerGenreRestAdapter restAdapter, GenreMapper genreMapper,
                      TotalValueMapper totalValueMapper) {
        this.restAdapter = restAdapter;
        this.genreMapper = genreMapper;
        this.totalValueMapper = totalValueMapper;
    }

    @DebugLog @Override
    public Observable<List<Genre>> genres() {
        return restAdapter.genres().map(genreMapper::map);
    }

    @DebugLog @Override
    public Observable<Genre> genre(String name) {
        return restAdapter.genre(name).map(genreMapper::map);
    }

    @DebugLog @Override
    public Observable<TotalValue> total() {
        return restAdapter.total().map(totalValueMapper::map);
    }
}
