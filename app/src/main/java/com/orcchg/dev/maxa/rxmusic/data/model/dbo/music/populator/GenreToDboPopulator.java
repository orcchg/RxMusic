package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper.GenreNameToDboMapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Populator;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;

public class GenreToDboPopulator implements Populator<Genre, GenreDBO> {

    private final GenreNameToDboMapper mapper;

    @Inject
    public GenreToDboPopulator(GenreNameToDboMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void populate(Genre object, GenreDBO dbo) {
        dbo.name = object.name();
        dbo.genres = new RealmList<>();
        List<String> list = object.genres();
        if (list != null) {
            for (String item : list) {
                dbo.genres.add(mapper.map(item));
            }
        }
    }
}
