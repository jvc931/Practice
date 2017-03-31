package com.globant.practice.di;

import com.globant.practice.view.activities.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component that contains all the possible reference that can inject whit Dagger.
 * Created by jonathan.vargas on 30/03/2017.
 */
@Singleton
@Component(modules = {PracticeModule.class})
public interface ApplicationComponent {
    /**
     * Inject method for the SplashActivity instance.
     * @param activity is the type SplashActivity
     */
    void inject(SplashActivity activity);
}
