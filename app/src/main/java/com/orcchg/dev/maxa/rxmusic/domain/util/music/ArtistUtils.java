package com.orcchg.dev.maxa.rxmusic.domain.util.music;

import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;

public class ArtistUtils {

    /**
     * Calculates a grade of a given {@link Artist} as a ratio between
     * {@link Artist#tracksCount} and {@link Artist#albumsCount}.
     * The more tracks and less albums a certain musician has the better
     * grade it has been given.
     *
     * @param artist Input model
     * @return grade of musician
     */
    public static int calculateGrade(Artist artist) {
        float ratio = artist.tracksCount() / (float) artist.albumsCount();
        if (ratio > 10.0f) {
            return 10;
        }
        return (int) ratio;
    }
}
