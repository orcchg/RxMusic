package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music;

import io.realm.RealmList;
import io.realm.RealmObject;

public class GenreDBO extends RealmObject {
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENRES = "genres";

    public String name;
    public RealmList<GenreNameDBO> genres;
}
