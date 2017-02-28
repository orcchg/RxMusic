package com.orcchg.dev.maxa.rxmusic.domain.interactor.music;

import android.support.annotation.Nullable;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.base.UseCase;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.SmallArtist;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetArtists extends UseCase<List<SmallArtist>> {

    private final ArtistRepository artistRepository;
    private @Nullable Parameters parameters;

    @Inject
    public GetArtists(ArtistRepository artistRepository, ThreadExecutor threadExecutor,
                      PostExecuteScheduler postExecuteScheduler) {
        super(threadExecutor, postExecuteScheduler);
        this.artistRepository = artistRepository;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    protected Observable<List<SmallArtist>> doAction() {
        if (parameters != null) {
            return artistRepository.artists(parameters.limit, parameters.offset, parameters.genres);
        }
        return artistRepository.artists();
    }

    /* Parameters */
    // --------------------------------------------------------------------------------------------
    public static class Parameters {
        int limit = -1;
        int offset = 0;
        List<String> genres;

        Parameters(Builder builder) {
            this.limit = builder.limit;
            this.offset = builder.offset;
            this.genres = builder.genres;
        }

        public static class Builder {
            int limit = -1;
            int offset = 0;
            List<String> genres;

            public Builder setLimit(int limit) {
                this.limit = limit;
                return this;
            }

            public Builder setOffset(int offset) {
                this.offset = offset;
                return this;
            }

            public Builder setGenres(List<String> genres) {
                this.genres = genres;
                return this;
            }

            public Parameters build() {
                return new Parameters(this);
            }
        }
    }
}
