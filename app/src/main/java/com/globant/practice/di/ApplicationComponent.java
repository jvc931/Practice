package com.globant.practice.di;

import com.globant.practice.presentation.view.activity.HomeActivity;
import com.globant.practice.presentation.view.activity.SplashActivity;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import com.globant.practice.presentation.view.fragment.SubscriberListFragment;
import com.globant.practice.presentation.view.fragment.WebClientFragment;
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
     * Inject method for the SubscriberListFragment
     *
     * @param fragment is the type SubscriberListFragment
     */
    void inject(SubscriberListFragment fragment);

    /**
     * Inject method for the SubscriberDetailsFragment
     *
     * @param fragment is the type SubscriberDetailsFragment
     */
    void inject(SubscriberDetailsFragment fragment);

    /**
     * Inject method for the WebClientFragment
     *
     * @param fragment is the type WebClientFragment
     */
    void inject(WebClientFragment fragment);
}
