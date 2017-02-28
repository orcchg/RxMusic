package com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.source.repository.DataSource;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import rx.Observable;

/**
 * Source of {@link Genre} data.
 */
public interface GenreDataSource extends DataSource {

    Observable<List<Genre>> genres();

    Observable<Genre> genre(String name);

    Observable<TotalValue> total();
}
