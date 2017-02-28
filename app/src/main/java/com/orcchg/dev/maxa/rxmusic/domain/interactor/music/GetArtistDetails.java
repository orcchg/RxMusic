package com.orcchg.dev.maxa.rxmusic.domain.interactor.music;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.base.UseCase;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Artist;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetArtistDetails extends UseCase<Artist> {

    private final long artistId;
    private final ArtistRepository artistRepository;

    @Inject
    public GetArtistDetails(long artistId, ArtistRepository artistRepository,
                            ThreadExecutor threadExecutor, PostExecuteScheduler postExecuteScheduler) {
        super(threadExecutor, postExecuteScheduler);
        this.artistId = artistId;
        this.artistRepository = artistRepository;
    }

    @Override
    protected Observable<Artist> doAction() {
        return artistRepository.artist(artistId);
    }
}
