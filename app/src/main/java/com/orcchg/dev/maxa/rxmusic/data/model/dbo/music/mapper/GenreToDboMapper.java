package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreNameDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.GenreToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.DuplexMapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GenreToDboMapper implements DuplexMapper<Genre, GenreDBO> {

    private final GenreNameToDboMapper mapper;
    private final GenreToDboPopulator populator;

    @Inject
    public GenreToDboMapper(GenreNameToDboMapper mapper, GenreToDboPopulator populator) {
        this.mapper = mapper;
        this.populator = populator;
    }

    /* Direct mapping */
    // ------------------------------------------
    @Override
    public GenreDBO map(Genre object) {
        GenreDBO dbo = new GenreDBO();
        populator.populate(object, dbo);
        return dbo;
    }

    @Override
    public List<GenreDBO> map(List<Genre> list) {
        List<GenreDBO> mapped = new ArrayList<>();
        for (Genre item : list) {
            mapped.add(map(item));
        }
        return mapped;
    }

    /* Backward mapping */
    // ------------------------------------------
    @Override
    public Genre mapBack(GenreDBO object) {
        List<String> genres = new ArrayList<>();
        for (GenreNameDBO dbo : object.genres) {
            genres.add(mapper.mapBack(dbo));
        }
        return Genre.builder()
                .setName(object.name)
                .setGenres(genres)
                .build();
    }

    @Override
    public List<Genre> mapBack(List<GenreDBO> list) {
        List<Genre> mapped = new ArrayList<>();
        for (GenreDBO item : list) {
            mapped.add(mapBack(item));
        }
        return mapped;
    }
}
