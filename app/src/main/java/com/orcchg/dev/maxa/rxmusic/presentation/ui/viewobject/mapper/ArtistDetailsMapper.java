package com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.mapper;

import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistDetailsVO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistDetailsMapper implements Mapper<Artist, ArtistDetailsVO> {

    @Inject
    public ArtistDetailsMapper() {
    }

    @Override
    public ArtistDetailsVO map(Artist object) {
        return ArtistDetailsVO.builder()
                .setId(object.id())
                .setName(object.name())
                .setCoverLarge(object.coverLarge())
                .setDescription(object.description())
                .setGenres(object.genres())
                .setLink(object.link())
                .setAlbumsCount(object.albumsCount())
                .setTracksCount(object.tracksCount())
                .build();
    }

    @Override
    public List<ArtistDetailsVO> map(List<Artist> list) {
        List<ArtistDetailsVO> mapped = new ArrayList<>();
        for (Artist artist : list) {
            mapped.add(map(artist));
        }
        return mapped;
    }
}
