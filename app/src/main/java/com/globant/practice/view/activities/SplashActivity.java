package com.globant.practice.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.globant.practice.PracticeApplication;
import com.globant.practice.presenter.SplashPresenter;
import com.globant.practice.R;
import javax.inject.Inject;

/**
 * Initializes the components of the activity_splash UI and manages the communication
 * with the presenter, implements the interface Splash.
 * Created by jonathan.vargas on 30/03/2017.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {
    @Inject
    SplashPresenter presenter;

    /**
     * Initializes the UI components and the presenter instance.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((PracticeApplication) getApplication()).getApplicationComponent().inject(this);
    }

    /**
     * Initializes the time that have to wait to change the View.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.timeToWaitForChangeView();
    }

    /**
     * Informs the presenter that have to detach de View
     */
    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    /**
     * Informs the presenter that the view is not showing.
     */
    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopWaitTimeToChangeView();
    }

    /**
     * Change the view to the HomeView when the time to wait is finish.
     */
    @Override
    public void navigateToHome() {
        Intent toHome = new Intent(this, HomeActivity.class);
        startActivity(toHome);
    }
}
