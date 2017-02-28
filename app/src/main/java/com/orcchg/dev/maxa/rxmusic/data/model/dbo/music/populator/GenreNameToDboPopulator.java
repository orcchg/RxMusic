package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreNameDBO;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Populator;

import javax.inject.Inject;

public class GenreNameToDboPopulator implements Populator<String, GenreNameDBO> {

    @Inject
    public GenreNameToDboPopulator() {
    }

    @Override
    public void populate(String str, GenreNameDBO dbo) {
        dbo.name = str;
    }
}
