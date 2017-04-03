package com.globant.practice.presenter;

import com.globant.practice.interfaces.Splash;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

/**
 * Contains the methods that the SplashActivity needs.
 * Created by jonathan.vargas on 30/03/2017.
 */

public class SplashPresenter {
    private Splash view;
    private boolean firstTimeThatRun = true;
    private static final int timeToWait = 3500;
    private Timer t;

    /**
     * Construct method of the SplashPresenter.
     */
    @Inject
    public SplashPresenter() {
    }

    /**
     * Receive and assign a instance of the view interface.
     *
     * @param splash instance of the view interface
     */
    public void attachView(Splash splash) {
        this.view = splash;
    }

    /**
     * Create and run the timer that count the time to change to the other view,
     */
    public void timeToWaitForChangeView() {
        if (firstTimeThatRun) {
            t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (verifyIfTheViewExist())
                        view.changeView();
                    else
                        firstTimeThatRun = true;
                }
            }, timeToWait);
        }
    }

    /**
     * Detach the instance of the view and change the flag that indicates
     * if is the first time that the application is invoked.
     */
    public void detachView() {
        view = null;
        firstTimeThatRun = false;
    }

    /**
     * Stop the timer to prevent a NullPointerException because the instance
     * of the view is not setting and change the flag that indicates if is
     * the first time that the application is invoked.
     */
    public void stopWaitTimeToChangeView() {
        t.cancel();
        firstTimeThatRun = true;
    }

    private boolean verifyIfTheViewExist() {
        if (view != null)
            return true;
        else
            return false;
    }
}
