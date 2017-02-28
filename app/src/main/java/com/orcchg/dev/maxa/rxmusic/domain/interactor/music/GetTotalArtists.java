package com.orcchg.dev.maxa.rxmusic.domain.interactor.music;

import android.support.annotation.Nullable;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.base.UseCase;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetTotalArtists extends UseCase<TotalValue> {

    private final ArtistRepository artistRepository;
    private @Nullable Parameters parameters;

    @Inject
    public GetTotalArtists(ArtistRepository artistRepository, ThreadExecutor threadExecutor,
                           PostExecuteScheduler postExecuteScheduler) {
        super(threadExecutor, postExecuteScheduler);
        this.artistRepository = artistRepository;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    protected Observable<TotalValue> doAction() {
        if (parameters != null) {
            List<String> genres = parameters.genres;
            return artistRepository.total(genres);
        }
        return artistRepository.total();
    }

    /* Parameters */
    // --------------------------------------------------------------------------------------------
    public static class Parameters {
        private final List<String> genres;

        Parameters(Builder builder) {
            this.genres = builder.genres;
        }

        public static class Builder {
            private List<String> genres;

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
