package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.activity.SplashView;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

/**
 * Contains the methods that the SplashActivity needs.
 * Created by jonathan.vargas on 30/03/2017.
 */
public class SplashPresenter extends BasePresenter<SplashView> {
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
     * Create and run the timer that count the time to change to the other view,
     */
    public void timeToWaitForChangeView() {
        if (firstTimeThatRun) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isViewAttached()) {
                        view.navigateToHome();
                    } else {
                        firstTimeThatRun = true;
                    }
                }
            }, TIME_TO_WAIT);
        }
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

    /**
     * Detach the instance of the view and change the flag that indicates
     * if is the first time that the application is invoked.
     */
    @Override
    public void detachView() {
        view = null;
        firstTimeThatRun = false;
    }
}
