package com.globant.practice.Di;

import com.globant.practice.View.Activities.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jonathan.vargas on 30/03/2017.
 */
@Singleton
@Component(modules = {PracticeModule.class})
public interface ApplicationComponent {
    void inject(SplashActivity activity);
}
