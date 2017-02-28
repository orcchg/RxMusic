package com.orcchg.dev.maxa.rxmusic.data.source.remote.music.artist;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.ArtistEntity;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.SmallArtistEntity;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.TotalValueEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerArtistRestAdapter {

    String ENDPOINT = "http://194.190.63.108:9123/";

    @GET("/all")
    Observable<List<SmallArtistEntity>> artists(@Query("limit") Integer limit,
            @Query("offset") Integer offset, @Query("genres") String genres);

    @GET("/single")
    Observable<ArtistEntity> artist(@Query("id") long artistId);

    @GET("/total")
    Observable<TotalValueEntity> total(@Query("genres") String genres);
}
