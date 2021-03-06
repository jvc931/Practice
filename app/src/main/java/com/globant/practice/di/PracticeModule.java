package com.globant.practice.di;

import com.globant.practice.BuildConfig;
import com.globant.practice.domain.service.GitHubApi;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides all the possible reference that Dagger can inject.
 * Created by jonathan.vargas on 30/03/2017.
 */
@Module
public class PracticeModule {

    /**
     * Returns a unique reference of retrofit.
     *
     * @return retrofit instance
     */
    @Provides
    @Singleton
    GitHubApi provideGitHubApi() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit.create(GitHubApi.class);
    }
}
