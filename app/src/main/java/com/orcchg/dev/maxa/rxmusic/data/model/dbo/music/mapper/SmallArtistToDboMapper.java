package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.SmallArtistDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.SmallArtistToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.DuplexMapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SmallArtistToDboMapper implements DuplexMapper<SmallArtist, SmallArtistDBO> {

    private final SmallArtistToDboPopulator populator;

    @Inject
    public SmallArtistToDboMapper(SmallArtistToDboPopulator populator) {
        this.populator = populator;
    }

    /* Direct mapping */
    // ------------------------------------------
    @Override
    public SmallArtistDBO map(SmallArtist object) {
        SmallArtistDBO dbo = new SmallArtistDBO();
        populator.populate(object, dbo);
        return dbo;
    }

    @Override
    public List<SmallArtistDBO> map(List<SmallArtist> list) {
        List<SmallArtistDBO> mapped = new ArrayList<>();
        for (SmallArtist item : list) {
            mapped.add(map(item));
        }
        return mapped;
    }

    /* Backward mapping */
    // ------------------------------------------
    @Override
    public SmallArtist mapBack(SmallArtistDBO object) {
        return SmallArtist.builder()
                .setId(object.id)
                .setName(object.name)
                .setCoverSmall(object.coverSmall)
                .build();
    }

    @Override
    public List<SmallArtist> mapBack(List<SmallArtistDBO> list) {
        List<SmallArtist> mapped = new ArrayList<>();
        for (SmallArtistDBO item : list) {
            mapped.add(mapBack(item));
        }
        return mapped;
    }
}
