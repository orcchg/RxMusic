package com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.GenreEntity;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GenreMapper implements Mapper<GenreEntity, Genre> {

    @Inject
    public GenreMapper() {
    }

    @Override
    public Genre map(GenreEntity object) {
        return Genre.builder()
                .setName(object.name())
                .setGenres(object.genres())
                .build();
    }

    @Override
    public List<Genre> map(List<GenreEntity> list) {
        List<Genre> mapped = new ArrayList<>();
        for (GenreEntity entity : list) {
            mapped.add(map(entity));
        }
        return mapped;
    }
}
