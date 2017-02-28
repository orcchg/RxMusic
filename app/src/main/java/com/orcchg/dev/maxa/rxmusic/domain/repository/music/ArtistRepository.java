package com.orcchg.dev.maxa.rxmusic.domain.repository.music;

import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.domain.repository.IRepository;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Artist} related data.
 */
public interface ArtistRepository extends IRepository {

    /**
     * Get an {@link rx.Observable} which will emit a List of {@link SmallArtist}.
     */
    Observable<List<SmallArtist>> artists();

    Observable<List<SmallArtist>> artists(int limit, int offset, List<String> genres);

    /**
     * Get an {@link rx.Observable} which will emit a {@link Artist}.
     *
     * @param artistId The Artist id used to retrieve Artist data.
     */
    Observable<Artist> artist(long artistId);

    /**
     * Get an {@link rx.Observable} which will emit total number of {@link Artist} items.
     */
    Observable<TotalValue> total();

    /**
     * Get an {@link rx.Observable} which will emit total number of {@link Artist} items.
     *
     * @param genres Specifies a list of genres to filter received {@link Artist} items by.
     */
    Observable<TotalValue> total(List<String> genres);
}
