package com.orcchg.dev.maxa.rxmusic.data.model.entity.music;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.orcchg.dev.maxa.rxmusic.domain.util.DomainConstant;

@AutoValue
public abstract class SmallArtistEntity {

    @SerializedName("id")    public abstract long id();
    @SerializedName("name")  public abstract String name();
    @SerializedName("cover") public abstract String coverSmall();

    public static TypeAdapter<SmallArtistEntity> typeAdapter(Gson gson) {
        return new AutoValue_SmallArtistEntity.GsonTypeAdapter(gson)
                .setDefaultId(DomainConstant.BAD_ID)
                .setDefaultName("")
                .setDefaultCoverSmall("");
    }
}
