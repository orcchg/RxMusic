package com.orcchg.dev.maxa.rxmusic.domain.interactor.music;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.base.UseCase;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.Genre;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.GenreRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetGenres extends UseCase<List<Genre>> {

    private final GenreRepository genreRepository;

    @Inject
    public GetGenres(GenreRepository genreRepository, ThreadExecutor threadExecutor,
                  PostExecuteScheduler postExecuteScheduler) {
        super(threadExecutor, postExecuteScheduler);
        this.genreRepository = genreRepository;
    }

    @Override
    protected Observable<List<Genre>> doAction() {
        return genreRepository.genres();
    }
}
