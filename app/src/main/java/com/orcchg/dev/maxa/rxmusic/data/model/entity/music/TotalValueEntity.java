package com.orcchg.dev.maxa.rxmusic.data.model.entity.music;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class TotalValueEntity {

    @SerializedName("value") public abstract int value();

    public static TypeAdapter<TotalValueEntity> typeAdapter(Gson gson) {
        return new AutoValue_TotalValueEntity.GsonTypeAdapter(gson)
                .setDefaultValue(0);
    }
}
