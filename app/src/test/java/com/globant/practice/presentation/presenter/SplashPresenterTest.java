package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.activity.SplashView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Unit test for the methods of the SplashPresenter.
 * Created by jonathan.vargas on 4/04/2017.
 */
public class SplashPresenterTest {
    @Mock
    private SplashPresenter presenter;
    @Mock
    private SplashView mockView;

    /**
     * Initializes the presenter and the mockView.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mockView);
    }

    /**
     * Unit test for timeToWaitForChangeView method that have to wait the set time
     * without errors.
     *
     * @throws Exception
     */
    @Test
    public void timeToWaitForChangeView_ShouldWaitTheTimeToChangeTheView() throws Exception {
        when(presenter.isViewAttached()).thenReturn(true);
        presenter.timeToWaitForChangeView();
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            fail("Timer gets an Exception.");
        }
    }

    /**
     * Unit test for stopWaitTimeToChangeView method, have to call first
     * timeToWaitForChangeView method to prevent the NullPointerException.
     *
     * @throws Exception
     */
    @Test
    public void stopWaitTimeToChangeView_ShouldRunWithoutErrors() throws Exception {
        when(presenter.isViewAttached()).thenReturn(true);
        presenter.timeToWaitForChangeView();
        try {
            presenter.stopWaitTimeToChangeView();
        } catch (NullPointerException e) {
            fail("Timer is not initialised.");
        }
    }
}