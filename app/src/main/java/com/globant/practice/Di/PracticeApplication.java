package com.globant.practice.Di;

import android.app.Application;


/**
 * Created by jonathan.vargas on 30/03/2017.
 */

public class PracticeApplication extends Application {
    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
