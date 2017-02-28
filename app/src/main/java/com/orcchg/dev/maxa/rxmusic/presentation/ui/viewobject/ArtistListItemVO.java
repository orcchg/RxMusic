package com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ArtistListItemVO {

    public static Builder builder() {
        return new AutoValue_ArtistListItemVO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);
        public abstract Builder setCoverSmall(String coverSmall);
        public abstract ArtistListItemVO build();
    }

    public abstract long id();
    public abstract String name();
    public abstract String coverSmall();
}
