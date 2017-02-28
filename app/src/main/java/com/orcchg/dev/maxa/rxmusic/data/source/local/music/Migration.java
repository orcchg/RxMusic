package com.orcchg.dev.maxa.rxmusic.data.source.local.music;

import com.orcchg.dev.maxa.rxmusic.data.source.local.music.artist.ArtistMigration;
import com.orcchg.dev.maxa.rxmusic.data.source.local.music.genre.GenreMigration;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

@Singleton
public class Migration implements RealmMigration {

    private final ArtistMigration artistMigration;
    private final GenreMigration genreMigration;

    @Inject
    public Migration(ArtistMigration artistMigration, GenreMigration genreMigration) {
        this.artistMigration = artistMigration;
        this.genreMigration = genreMigration;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        artistMigration.migrate(realm, oldVersion, newVersion);
        genreMigration.migrate(realm, oldVersion, newVersion);
    }

    @Override
    public int hashCode() {
        return 17;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Migration);
    }
}
