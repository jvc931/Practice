package com.globant.practice.Presenter;

import android.util.Log;

import com.globant.practice.View.Activities.SplashActivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by jonathan.vargas on 30/03/2017.
 */

public class SplashPresenter {
    SplashActivity splash;
    private boolean firstTime = true;
    private int timeToWait = 3500;

    @Inject
    public SplashPresenter() {
    }

    public void attachView(SplashActivity splash) {
        this.splash = splash;
    }

    public void countTime() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                splash.changeView();
            }
        }, timeToWait);
    }

    public void detachView() {
        firstTime = false;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
