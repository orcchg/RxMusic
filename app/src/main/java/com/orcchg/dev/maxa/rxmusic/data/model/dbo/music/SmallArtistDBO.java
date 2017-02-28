package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music;

import io.realm.RealmObject;

public class SmallArtistDBO extends RealmObject {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COVER_SMALL = "coverSmall";

    public long id;
    public String name;
    public String coverSmall;
}
