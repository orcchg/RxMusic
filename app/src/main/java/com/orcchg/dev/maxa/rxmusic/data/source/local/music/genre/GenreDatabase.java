package com.orcchg.dev.maxa.rxmusic.data.source.local.music.genre;

import android.text.TextUtils;

import com.orcchg.dev.maxa.rxmusic.data.injection.local.DaggerMigrationComponent;
import com.orcchg.dev.maxa.rxmusic.data.injection.local.MigrationComponent;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.GenreToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.GenreToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.genre.GenreDataSource;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

@Singleton
public class GenreDatabase implements GenreDataSource {

    private final GenreToDboMapper mapper;
    private final GenreToDboPopulator populator;

    private final MigrationComponent migrationComponent;

    @Inject
    public GenreDatabase(GenreToDboMapper mapper, GenreToDboPopulator populator) {
        this.mapper = mapper;
        this.populator = populator;
        this.migrationComponent = DaggerMigrationComponent.create();
    }

    /* Create */
    // ------------------------------------------

    /* Read */
    // ------------------------------------------
    @Override
    public Observable<List<Genre>> genres() {
        Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
        RealmResults<GenreDBO> dbos = realm.where(GenreDBO.class).findAll();
        List<Genre> models = mapper.mapBack(dbos);
        realm.close();
        return Observable.just(models);
    }

    @Override
    public Observable<Genre> genre(String name) {
        if (!TextUtils.isEmpty(name)) {
            Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
            Genre model = null;
            GenreDBO dbo = realm.where(GenreDBO.class).equalTo(GenreDBO.COLUMN_NAME, name).findFirst();
            if (dbo != null) model = mapper.mapBack(dbo);
            realm.close();
            if (model != null) return Observable.just(model);
        }
        return Observable.empty();
    }

    @Override
    public Observable<TotalValue> total() {
        Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
        RealmResults<GenreDBO> dbos = realm.where(GenreDBO.class).findAll();
        TotalValue total = TotalValue.builder().setValue(dbos.size()).build();
        realm.close();
        return Observable.just(total);
    }

    /* Update */
    // ------------------------------------------

    /* Delete */
    // ------------------------------------------
}
