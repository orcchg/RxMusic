package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreNameDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.GenreNameToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.DuplexMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GenreNameToDboMapper implements DuplexMapper<String, GenreNameDBO> {

    private final GenreNameToDboPopulator populator;

    @Inject
    public GenreNameToDboMapper(GenreNameToDboPopulator populator) {
        this.populator = populator;
    }

    /* Direct mapping */
    // ------------------------------------------
    @Override
    public GenreNameDBO map(String object) {
        GenreNameDBO dbo = new GenreNameDBO();
        populator.populate(object, dbo);
        return dbo;
    }

    @Override
    public List<GenreNameDBO> map(List<String> list) {
        List<GenreNameDBO> mapped = new ArrayList<>();
        for (String item : list) {
            mapped.add(map(item));
        }
        return mapped;
    }

    /* Backward mapping */
    // ------------------------------------------
    @Override
    public String mapBack(GenreNameDBO object) {
        return object.name;
    }

    @Override
    public List<String> mapBack(List<GenreNameDBO> list) {
        List<String> mapped = new ArrayList<>();
        for (GenreNameDBO item : list) {
            mapped.add(mapBack(item));
        }
        return mapped;
    }
}
