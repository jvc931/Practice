package com.globant.practice.di;

import com.globant.practice.BuildConfig;
import com.globant.practice.domain.interactor.FetchUsers;
import com.globant.practice.domain.service.GitHubApi;
import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.presenter.SplashPresenter;
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
     * Returns a unique reference of SplashPresenter.
     *
     * @return SplashPresenter reference
     */
    @Provides
    @Singleton
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }

    /**
     * Returns a unique reference of HomePresenter.
     *
     * @param interactor needs an UsersInteractor reference
     * @return HomePresenter reference
     */
    @Provides
    @Singleton
    HomePresenter provideHomePresenter(FetchUsers interactor) {
        return new HomePresenter(interactor);
    }

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
