package com.globant.practice.di;

import com.globant.practice.domain.interactor.UserInteractor;
import com.globant.practice.domain.service.ProvideGithubApi;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.presenter.SplashPresenter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

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
    HomePresenter provideHomePresenter(UserInteractor interactor) {
        return new HomePresenter(interactor);
    }

    /**
     * Returns a unique reference of GitnubApi.
     *
     * @return GithubApi reference
     */
    @Provides
    @Singleton
    ProvideGithubApi provideProvideGithubApi() {
        return new ProvideGithubApi();
    }
}
