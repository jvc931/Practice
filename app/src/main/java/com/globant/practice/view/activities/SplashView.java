package com.globant.practice.view.activities;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */

public interface SplashView {

    /**
     * Creates and starts the intent to change the view to HomeActivity.
     */
    void navigateToHome();
}
