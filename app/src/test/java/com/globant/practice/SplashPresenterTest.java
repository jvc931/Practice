package com.globant.practice;

import com.globant.practice.interfaces.Splash;
import com.globant.practice.presenter.SplashPresenter;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit test for the methods of the SplashPresenter.
 * Created by jonathan.vargas on 31/03/2017.
 */

public class SplashPresenterTest {

    private SplashPresenter presenter = new SplashPresenter();
    Splash mockView = Mockito.mock(Splash.class);

    /**
     * Unit test for the timeToWaitForChangeView method.
     */
    @Test
    public void timeToWaitForChangeView_ShouldWaitTheTimeToChangeTheView() {
        presenter.timeToWaitForChangeView();
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unit Test for the attachView method.
     */
    @Test
    public void attachView_ShouldSaveTheInstanceOfTheInterface() {
        presenter.attachView(mockView);
    }

}
