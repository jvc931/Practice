package com.globant.practice.presentation.view.activity;

import com.globant.practice.presentation.view.BaseView;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public interface SplashView extends BaseView {

    /**
     * Creates and starts the intent to change the view to HomeActivity.
     */
    void navigateToHome();
}
