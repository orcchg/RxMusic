package com.orcchg.dev.maxa.rxmusic.data.source.local.music.artist;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.orcchg.dev.maxa.rxmusic.data.injection.local.DaggerMigrationComponent;
import com.orcchg.dev.maxa.rxmusic.data.injection.local.MigrationComponent;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.ArtistDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.SmallArtistDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.ArtistToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.SmallArtistToDboMapper;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.ArtistToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.RepoUtility;
import com.orcchg.dev.maxa.rxmusic.data.source.repository.music.artist.ArtistDataSource;
import com.orcchg.dev.maxa.rxmusic.domain.util.DomainConstant;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

@Singleton
public class ArtistDatabase implements ArtistDataSource {

    private final ArtistToDboMapper artistMapper;
    private final SmallArtistToDboMapper smallArtistMapper;
    private final ArtistToDboPopulator populator;

    private final MigrationComponent migrationComponent;

    @Inject
    public ArtistDatabase(ArtistToDboMapper artistMapper, SmallArtistToDboMapper smallArtistMapper,
                          ArtistToDboPopulator populator) {
        this.artistMapper = artistMapper;
        this.smallArtistMapper = smallArtistMapper;
        this.populator = populator;
        this.migrationComponent = DaggerMigrationComponent.create();
    }

    /* Create */
    // ------------------------------------------

    /* Read */
    // ------------------------------------------
    @DebugLog @Override
    public Observable<List<SmallArtist>> artists() {
        return artists(-1, 0);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(int limit, int offset) {
        return artists(limit, offset, null);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(@Nullable List<String> genres) {
        return artists(-1, 0, genres);
    }

    @DebugLog @Override
    public Observable<List<SmallArtist>> artists(int limit, int offset, @Nullable List<String> genres) {
        RepoUtility.checkLimitAndOffset(limit, offset);
        Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
        RealmResults<SmallArtistDBO> dbos = realm.where(SmallArtistDBO.class).findAll();
        List<SmallArtist> models = new ArrayList<>();
        int size = limit < 0 ? dbos.size() : limit;
        RepoUtility.checkListBounds(offset + size - 1, dbos.size());
        for (int i = offset; i < offset + size; ++i) {
            models.add(smallArtistMapper.mapBack(dbos.get(i)));
        }
        realm.close();
        return Observable.just(models);
    }

    @DebugLog @Override
    public Observable<Artist> artist(long artistId) {
        if (artistId != DomainConstant.BAD_ID) {
            Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
            Artist model = null;
            ArtistDBO dbo = realm.where(ArtistDBO.class).equalTo(ArtistDBO.COLUMN_ID, artistId).findFirst();
            if (dbo != null) model = artistMapper.mapBack(dbo);
            realm.close();
            if (model != null) return Observable.just(model);
        }
        return Observable.empty();
    }

    @DebugLog @Override
    public Observable<TotalValue> total() {
        Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
        RealmResults<SmallArtistDBO> dbos = realm.where(SmallArtistDBO.class).findAll();
        TotalValue total = TotalValue.builder().setValue(dbos.size()).build();
        realm.close();
        return Observable.just(total);
    }

    @DebugLog @Override
    public Observable<TotalValue> total(@Nullable List<String> genres) {
        if (genres != null && !genres.isEmpty()) {
            String genresStr = TextUtils.join(",", genres);
            Realm realm = Realm.getInstance(migrationComponent.realmConfiguration());
            RealmResults<ArtistDBO> dbos = realm.where(ArtistDBO.class).equalTo(ArtistDBO.COLUMN_GENRES, genresStr).findAll();
            TotalValue total = TotalValue.builder().setValue(dbos.size()).build();
            realm.close();
            return Observable.just(total);
        }
        return total();
    }

    /* Update */
    // ------------------------------------------

    /* Delete */
    // ------------------------------------------
}
