package com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class ArtistDetailsVO {

    public static Builder builder() {
        return new AutoValue_ArtistDetailsVO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);
        public abstract Builder setCoverLarge(String coverLarge);
        public abstract Builder setDescription(String description);
        public abstract Builder setGenres(List<String> genres);
        public abstract Builder setLink(String link);
        public abstract Builder setAlbumsCount(int albumsCount);
        public abstract Builder setTracksCount(int tracksCount);
        public abstract ArtistDetailsVO build();
    }

    public abstract long id();
    public abstract String name();
    public abstract String coverLarge();
    public abstract String description();
    public abstract List<String> genres();
    public abstract String link();
    public abstract int albumsCount();
    public abstract int tracksCount();
}
