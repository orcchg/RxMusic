package com.orcchg.dev.maxa.rxmusic.domain.repository.music;

import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Genre} related data.
 */
public interface GenreRepository {

    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Genre}.
     */
    Observable<List<Genre>> genres();

    /**
     * Get an {@link rx.Observable} which will emit total number of {@link Genre} items.
     */
    Observable<TotalValue> total();
}
