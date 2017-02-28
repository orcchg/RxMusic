package com.orcchg.dev.maxa.rxmusic.domain.model.music;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TotalValue {

    public static Builder builder() {
        return new AutoValue_TotalValue.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setValue(int value);
        public abstract TotalValue build();
    }

    public abstract int value();
}
