package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.SmallArtistDBO;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Populator;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;

import javax.inject.Inject;

public class SmallArtistToDboPopulator implements Populator<SmallArtist, SmallArtistDBO> {

    @Inject
    public SmallArtistToDboPopulator() {
    }

    @Override
    public void populate(SmallArtist object, SmallArtistDBO dbo) {
        dbo.id = object.id();
        dbo.name = object.name();
        dbo.coverSmall = object.coverSmall();
    }
}
