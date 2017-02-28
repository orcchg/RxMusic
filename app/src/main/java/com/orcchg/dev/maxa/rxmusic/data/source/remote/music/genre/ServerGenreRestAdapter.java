package com.orcchg.dev.maxa.rxmusic.data.source.remote.music.genre;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.GenreEntity;
import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.TotalValueEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerGenreRestAdapter {

    String ENDPOINT = "http://194.190.63.108:9123/";

    @GET("/genres")
    Observable<List<GenreEntity>> genres();

    @GET("/genre")
    Observable<GenreEntity> genre(@Query("name") String name);

    @GET("/total_genres")
    Observable<TotalValueEntity> total();
}
