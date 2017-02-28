package com.orcchg.dev.maxa.rxmusic.domain.model.music;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Genre {

    public static Builder builder() {
        return new AutoValue_Genre.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String name);
        public abstract Builder setGenres(List<String> genres);
        public abstract Genre build();
    }

    public abstract String name();
    public abstract List<String> genres();
}
