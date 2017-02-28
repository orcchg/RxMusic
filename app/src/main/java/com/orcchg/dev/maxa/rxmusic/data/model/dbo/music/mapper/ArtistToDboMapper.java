package com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.ArtistDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.GenreNameDBO;
import com.orcchg.dev.maxa.rxmusic.data.model.dbo.music.populator.ArtistToDboPopulator;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.DuplexMapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistToDboMapper implements DuplexMapper<Artist, ArtistDBO> {

    private final GenreNameToDboMapper mapper;
    private final ArtistToDboPopulator populator;

    @Inject
    public ArtistToDboMapper(GenreNameToDboMapper mapper, ArtistToDboPopulator populator) {
        this.mapper = mapper;
        this.populator = populator;
    }

    /* Direct mapping */
    // ------------------------------------------
    @Override
    public ArtistDBO map(Artist object) {
        ArtistDBO dbo = new ArtistDBO();
        populator.populate(object, dbo);
        return dbo;
    }

    @Override
    public List<ArtistDBO> map(List<Artist> list) {
        List<ArtistDBO> mapped = new ArrayList<>();
        for (Artist item : list) {
            mapped.add(map(item));
        }
        return mapped;
    }

    /* Backward mapping */
    // ------------------------------------------
    @Override
    public Artist mapBack(ArtistDBO object) {
        List<String> genres = new ArrayList<>();
        for (GenreNameDBO dbo : object.genres) {
            genres.add(mapper.mapBack(dbo));
        }
        return Artist.builder()
                .setId(object.id)
                .setName(object.name)
                .setCoverLarge(object.coverLarge)
                .setCoverSmall(object.coverSmall)
                .setDescription(object.description)
                .setGenres(genres)
                .setLink(object.link)
                .setAlbumsCount(object.albumsCount)
                .setTracksCount(object.tracksCount)
                .build();
    }

    @Override
    public List<Artist> mapBack(List<ArtistDBO> list) {
        List<Artist> mapped = new ArrayList<>();
        for (ArtistDBO item : list) {
            mapped.add(mapBack(item));
        }
        return mapped;
    }
}
