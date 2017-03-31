package com.globant.practice;

import com.globant.practice.presenter.SplashPresenter;

import org.junit.Test;


import static org.junit.Assert.fail;

/**
 * Unit test for the methods of the SplashPresenter.
 * Created by jonathan.vargas on 31/03/2017.
 */

public class SplashPresenterTest {
    private SplashPresenter presenter = new SplashPresenter();

    /**
     * Unit test that expect a AssertionError because when the time to change ends
     * make a call using the view instance and this instance is null.
     */
    @Test(expected = AssertionError.class)
    public void countTime() {
        presenter.timeToWaitForChangeView();
        fail("The reference of SplashView is null");
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
