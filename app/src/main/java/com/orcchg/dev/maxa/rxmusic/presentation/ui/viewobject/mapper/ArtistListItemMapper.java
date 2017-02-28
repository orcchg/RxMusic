package com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.mapper;

import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistListItemVO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistListItemMapper implements Mapper<SmallArtist, ArtistListItemVO> {

    @Inject
    public ArtistListItemMapper() {
    }

    @Override
    public ArtistListItemVO map(SmallArtist object) {
        return ArtistListItemVO.builder()
                .setId(object.id())
                .setName(object.name())
                .setCoverSmall(object.coverSmall())
                .build();
    }

    @Override
    public List<ArtistListItemVO> map(List<SmallArtist> list) {
        List<ArtistListItemVO> mapped = new ArrayList<>();
        for (SmallArtist artist : list) {
            mapped.add(map(artist));
        }
        return mapped;
    }
}
