package com.orcchg.dev.maxa.rxmusic.data.source.local.music.genre;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

@Singleton
public class GenreMigration implements RealmMigration {

    @Inject
    public GenreMigration() {
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // no-op
    }
}
