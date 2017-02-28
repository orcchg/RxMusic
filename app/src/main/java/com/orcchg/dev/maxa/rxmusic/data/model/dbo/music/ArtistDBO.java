package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ArtistDBO extends RealmObject {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COVER_LARGE = "coverLarge";
    public static final String COLUMN_COVER_SMALL = "coverSmall";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_GENRES = "genres";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_ALBUMS_COUNT = "albumsCount";
    public static final String COLUMN_TRACKS_COUNT = "tracksCount";

    public long id;
    public String name;
    public String coverLarge;
    public String coverSmall;
    public String description;
    public RealmList<GenreNameDBO> genres;
    public String link;
    public int albumsCount;
    public int tracksCount;
}
