package com.orcchg.dev.maxa.rxmusic.presentation.injection.application;

import android.content.Context;

import com.orcchg.dev.maxa.rxmusic.domain.executor.JobExecutor;
import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;
import com.orcchg.dev.maxa.rxmusic.presentation.MusicApplication;
import com.orcchg.dev.maxa.rxmusic.presentation.executor.UiThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final MusicApplication application;

    public ApplicationModule(MusicApplication application) {
        this.application = application;
    }

    /* Context */
    // ------------------------------------------
    @Provides
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    /* Execution */
    // ------------------------------------------
    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides @Singleton
    PostExecuteScheduler providePostExecuteScheduler(UiThread uiThread) {
        return uiThread;
    }
}
