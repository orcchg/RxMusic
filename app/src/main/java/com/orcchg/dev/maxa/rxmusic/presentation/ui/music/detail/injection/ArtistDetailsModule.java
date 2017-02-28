package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail.injection;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.interactor.music.GetArtistDetails;
import com.orcchg.dev.maxa.rxmusic.domain.repository.music.ArtistRepository;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistDetailsModule {

    private final long artistId;

    /**
     * Constructor to configure and then instantiate the {@link DetailsModule} class.
     * It will be invoked when a dagger-generated implementation of {@link DetailsComponent}
     * will have been built.
     *
     * Sets a {@link ArtistDetailsModule#artistId} to create specific {@link GetArtistDetails} on demand.
     */
    public ArtistDetailsModule(long artistId) {
        this.artistId = artistId;
    }

    /**
     * Specifies how to actually construct an injectable instance of {@link GetArtistDetails} class.
     *
     * Despite {@link GetArtistDetails} class has an inject-constructor, we need to call it
     * to instantiate the class with {@link GetArtistDetails#artistId} specified, because the object
     * is configurable.
     *
     * @return an instance to inject
     */
    @Provides
    @PerActivity
    GetArtistDetails provideGetArtistDetails(ArtistRepository artistRepository,
                                             ThreadExecutor threadExecutor, PostExecuteScheduler postExecuteScheduler) {
        return new GetArtistDetails(artistId, artistRepository, threadExecutor, postExecuteScheduler);
    }
}
