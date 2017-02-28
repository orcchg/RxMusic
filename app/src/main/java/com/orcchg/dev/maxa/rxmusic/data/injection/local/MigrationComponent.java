package com.orcchg.dev.maxa.rxmusic.data.injection.local;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.RealmConfiguration;

@Singleton
@Component(modules = {MigrationModule.class})
public interface MigrationComponent {

    RealmConfiguration realmConfiguration();
}
