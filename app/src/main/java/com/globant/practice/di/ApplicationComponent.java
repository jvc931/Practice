package com.globant.practice.di;

import com.globant.practice.domain.interactor.UserInteractor;
import com.globant.practice.presentation.view.activity.HomeActivity;
import com.globant.practice.presentation.view.activity.SplashActivity;
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
     *
     * @param activity is the type SplashActivity
     */
    void inject(SplashActivity activity);

    /**
     * Inject method for the HomeActivity instance.
     *
     * @param activity is the type HomeActivity
     */
    void inject(HomeActivity activity);

    /**
     * Inject method for the UserInteractor instance
     *
     * @param userInteractor is the type UserInteractor
     */
    void inject(UserInteractor userInteractor);

}
