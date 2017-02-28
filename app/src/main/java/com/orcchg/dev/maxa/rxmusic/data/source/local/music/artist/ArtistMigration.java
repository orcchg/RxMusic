package com.orcchg.dev.maxa.rxmusic.data.source.local.music.artist;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

@Singleton
public class ArtistMigration implements RealmMigration {

    @Inject
    public ArtistMigration() {
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // no-op
    }
}
