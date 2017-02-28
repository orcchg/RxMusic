package com.orcchg.dev.maxa.rxmusic.domain.model.music;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SmallArtist {

    public static Builder builder() {
        return new AutoValue_SmallArtist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);
        public abstract Builder setCoverSmall(String coverSmall);
        public abstract SmallArtist build();
    }

    public abstract long id();
    public abstract String name();
    public abstract String coverSmall();
}
