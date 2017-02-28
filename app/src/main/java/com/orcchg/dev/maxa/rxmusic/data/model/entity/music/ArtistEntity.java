package com.orcchg.dev.maxa.rxmusic.data.model.entity.music;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.orcchg.dev.maxa.rxmusic.domain.util.DomainConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoValue
public abstract class ArtistEntity {
    public static final String COVER_LARGE = "big";
    public static final String COVER_SMALL = "small";

    @SerializedName("id")          public abstract long id();
    @SerializedName("name")        public abstract String name();
    @SerializedName("cover")       public abstract Map<String, String> covers();
    @SerializedName("description") public abstract String description();
    @SerializedName("genres")      public abstract List<String> genres();
    @SerializedName("link")        public abstract String link();
    @SerializedName("albums")      public abstract int albumsCount();
    @SerializedName("tracks")      public abstract int tracksCount();

    public static TypeAdapter<ArtistEntity> typeAdapter(Gson gson) {
        Map<String, String> covers = new HashMap<>();
        covers.put(COVER_LARGE, "");
        covers.put(COVER_SMALL, "");
        return new AutoValue_ArtistEntity.GsonTypeAdapter(gson)
                .setDefaultId(DomainConstant.BAD_ID)
                .setDefaultName("")
                .setDefaultCovers(covers)
                .setDefaultDescription("")
                .setDefaultGenres(new ArrayList<>())
                .setDefaultLink("")
                .setDefaultAlbumsCount(0)
                .setDefaultTracksCount(0);
    }
}
