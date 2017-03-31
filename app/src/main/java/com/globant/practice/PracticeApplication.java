package com.globant.practice;

import android.app.Application;

import com.globant.practice.di.ApplicationComponent;
import com.globant.practice.di.DaggerApplicationComponent;


/**
 * Base application for initialize Dagger.
 * Created by jonathan.vargas on 30/03/2017.
 */

public class PracticeApplication extends Application {
    private static ApplicationComponent component;

    /**
     * Initialize Dagger.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().build();
    }

    /**
     * Returns the application component.
     * @return application component
     */
    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
