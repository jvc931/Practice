package com.globant.practice.presenter;

import com.globant.practice.view.activities.SplashView;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

/**
 * Contains the methods that the SplashActivity needs.
 * Created by jonathan.vargas on 30/03/2017.
 */

public class SplashPresenter {
    private SplashView view;
    private boolean firstTimeThatRun = true;
    private static final int TIME_TO_WAIT = 3500;
    private Timer timer;

    /**
     * Construct method of the SplashPresenter.
     */
    @Inject
    public SplashPresenter() {
    }

    /**
     * Receives and assign an instance of the view interface.
     *
     * @param view instance of the view interface
     */
    public void attachView(SplashView view) {
        this.view = view;
    }

    /**
     * Create and run the timer that count the time to change to the other view,
     */
    public void timeToWaitForChangeView() {
        if (firstTimeThatRun) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isViewAttached()) {
                        view.changeView();
                    } else {
                        firstTimeThatRun = true;
                    }
                }
            }, TIME_TO_WAIT);
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
        timer.cancel();
        firstTimeThatRun = true;
    }

    /**
     * Checks if the view is attach for prevent a NullPointerException.
     *
     * @return True if the view is attach or else if not
     */
    private boolean isViewAttached() {
        return view != null;
    }
}
