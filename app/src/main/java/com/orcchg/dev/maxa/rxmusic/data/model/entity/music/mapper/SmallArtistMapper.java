package com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.SmallArtistEntity;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SmallArtistMapper implements Mapper<SmallArtistEntity, SmallArtist> {

    @Inject
    public SmallArtistMapper() {
    }

    @Override
    public SmallArtist map(SmallArtistEntity object) {
        return SmallArtist.builder()
                .setId(object.id())
                .setName(object.name())
                .setCoverSmall(object.coverSmall())
                .build();
    }

    @Override
    public List<SmallArtist> map(List<SmallArtistEntity> list) {
        List<SmallArtist> mapped = new ArrayList<>();
        for (SmallArtistEntity entity : list) {
            mapped.add(map(entity));
        }
        return mapped;
    }
}
