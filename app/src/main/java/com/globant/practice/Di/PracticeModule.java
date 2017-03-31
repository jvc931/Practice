package com.globant.practice.Di;

import com.globant.practice.Presenter.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jonathan.vargas on 30/03/2017.
 */
@Module
public class PracticeModule {

    @Provides
    @Singleton
    SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }
}
