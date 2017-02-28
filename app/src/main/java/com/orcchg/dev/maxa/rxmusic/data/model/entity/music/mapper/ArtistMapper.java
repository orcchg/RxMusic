package com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.ArtistEntity;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistMapper implements Mapper<ArtistEntity, Artist> {

    @Inject
    public ArtistMapper() {
    }

    @Override
    public Artist map(ArtistEntity object) {
        return Artist.builder()
                .setId(object.id())
                .setName(object.name())
                .setCoverSmall(object.covers().get(ArtistEntity.COVER_SMALL))
                .setCoverLarge(object.covers().get(ArtistEntity.COVER_LARGE))
                .setDescription(object.description())
                .setGenres(object.genres())
                .setLink(object.link())
                .setAlbumsCount(object.albumsCount())
                .setTracksCount(object.tracksCount())
                .build();
    }

    @Override
    public List<Artist> map(List<ArtistEntity> list) {
        List<Artist> mapped = new ArrayList<>();
        for (ArtistEntity entity : list) {
            mapped.add(map(entity));
        }
        return mapped;
    }
}
