package com.orcchg.dev.maxa.rxmusic.data.injection.local;

import com.orcchg.dev.maxa.rxmusic.data.source.local.music.Migration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public class MigrationModule {

    @Provides @Singleton
    RealmConfiguration provideRealmConfiguration(Migration migration) {
        return new RealmConfiguration.Builder()
                    .schemaVersion(1)
                    .migration(migration)
                    .build();
    }
}
