package com.globant.practice.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.globant.practice.Di.PracticeApplication;
import com.globant.practice.Presenter.SplashPresenter;
import com.globant.practice.R;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {
    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((PracticeApplication) getApplication()).getApplicationComponent().inject(this);
        presenter.attachView(this);
        if (presenter.isFirstTime()) {
            presenter.countTime();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    public void changeView() {
        Intent toHome = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(toHome);
    }
}
