package com.orcchg.dev.maxa.rxmusic.domain.interactor.music;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.base.UseCase;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.GenreRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetTotalGenres extends UseCase<TotalValue> {

    private final GenreRepository genreRepository;

    @Inject
    public GetTotalGenres(GenreRepository genreRepository, ThreadExecutor threadExecutor,
                          PostExecuteScheduler postExecuteScheduler) {
        super(threadExecutor, postExecuteScheduler);
        this.genreRepository = genreRepository;
    }

    @Override
    protected Observable<TotalValue> doAction() {
        return genreRepository.total();
    }
}
