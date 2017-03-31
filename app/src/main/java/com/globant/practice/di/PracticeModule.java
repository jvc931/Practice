package com.globant.practice.di;

import com.globant.practice.presenter.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide all the possible reference that Dagger can inject.
 * Created by jonathan.vargas on 30/03/2017.
 */
@Module
public class PracticeModule {
    /**
     * Returns a unique reference of SplashPresenter.
     * @return SplashPresenter reference
     */
    @Provides
    @Singleton
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }
}
