package com.orcchg.dev.maxa.rxmusic.data.model.entity.music;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class GenreEntity {

    @SerializedName("name")   public abstract String name();
    @SerializedName("genres") public abstract List<String> genres();

    public static TypeAdapter<GenreEntity> typeAdapter(Gson gson) {
        return new AutoValue_GenreEntity.GsonTypeAdapter(gson)
                .setDefaultName("")
                .setDefaultGenres(new ArrayList<>());
    }
}
