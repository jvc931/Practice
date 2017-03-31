package com.globant.practice;

import com.globant.practice.Presenter.SplashPresenter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by jonathan.vargas on 31/03/2017.
 */

public class SplashPresenterTest {
    private SplashPresenter presenter = new SplashPresenter();

    @Test
    public void isFirstTime_FirstTimeRun_ReturnsTrue() {
        assertTrue(presenter.isFirstTime());
    }

    @Test
    public void detachView_TheViewIsDetach_firstTimeIsFalse() {
        presenter.detachView();
        assertFalse(presenter.isFirstTime());
    }

    @Test(expected = AssertionError.class)
    public void countTime() {
        presenter.countTime();
        fail("The SplashView is null");
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
