package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.ArtistDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.GenreNameToDboMapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Populator;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;

public class ArtistToDboPopulator implements Populator<Artist, ArtistDBO> {

    private final GenreNameToDboMapper mapper;

    @Inject
    public ArtistToDboPopulator(GenreNameToDboMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void populate(Artist object, ArtistDBO dbo) {
        dbo.id = object.id();
        dbo.name = object.name();
        dbo.coverLarge = object.coverLarge();
        dbo.coverSmall = object.coverSmall();
        dbo.description = object.description();
        dbo.genres = new RealmList<>();
        dbo.link = object.link();
        dbo.albumsCount = object.albumsCount();
        dbo.tracksCount = object.tracksCount();
        List<String> list = object.genres();
        if (list != null) {
            for (String item : list) {
                dbo.genres.add(mapper.map(item));
            }
        }
    }
}
