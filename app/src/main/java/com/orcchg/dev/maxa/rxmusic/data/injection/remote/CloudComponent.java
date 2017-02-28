package com.orcchg.dev.maxa.rxmusic.data.injection.remote;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {CloudModule.class})
public interface CloudComponent {

    Retrofit.Builder retrofitBuilder();
}
