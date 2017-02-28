package com.orcchg.dev.maxa.rxmusic.domain.model.music;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Artist {

    public static Builder builder() {
        return new AutoValue_Artist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);
        public abstract Builder setCoverSmall(String coverSmall);
        public abstract Builder setCoverLarge(String coverLarge);
        public abstract Builder setDescription(String description);
        public abstract Builder setGenres(List<String> genres);
        public abstract Builder setLink(String link);
        public abstract Builder setAlbumsCount(int albumsCount);
        public abstract Builder setTracksCount(int tracksCount);
        public abstract Artist build();
    }

    public abstract long id();
    public abstract String name();
    public abstract String coverSmall();
    public abstract String coverLarge();
    public abstract String description();
    public abstract List<String> genres();
    public abstract String link();
    public abstract int albumsCount();
    public abstract int tracksCount();
}
