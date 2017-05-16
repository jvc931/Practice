package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.activity.HomeView;
import com.globant.practice.presentation.view.activity.SplashView;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsView;
import com.globant.practice.presentation.view.fragment.SubscriberListView;
import com.globant.practice.presentation.view.fragment.WebClientView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Unit test for the methods of BasePresenter
 * Created by jonathan.vargas on 16/05/2017.
 */
public class BasePresenterTest {
    @Mock
    private SplashView mockSplashView;
    @Mock
    private HomeView mockHomeView;
    @Mock
    private SubscriberListView mockSubscriberListView;
    @Mock
    private SubscriberDetailsView mockSubscriberDetailsView;
    @Mock
    WebClientView mockWebClientView;
    @Mock
    private BasePresenter presenter;

    @Before
    /**
     * Initializes the presenter and the mock views.
     */
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mockSplashView);
    }

    /**
     * Calls the attachView method using different types of views, after that verifies if the
     * method execute one time.
     */
    @Test
    public void attachView_passVerifyMethod() {
        verify(presenter).attachView(mockSplashView);
        presenter.attachView(mockHomeView);
        verify(presenter).attachView(mockHomeView);
        presenter.attachView(mockSubscriberListView);
        verify(presenter).attachView(mockSubscriberListView);
        presenter.attachView(mockSubscriberDetailsView);
        verify(presenter).attachView(mockSubscriberDetailsView);
        presenter.attachView(mockWebClientView);
        verify(presenter).attachView(mockWebClientView);
    }

    /**
     * Tests the detachView method by calling it and validating if the view is detached with
     * calling isViewAttached method that will be return false.
     */
    @Test
    public void detachView_ShouldReturnFalse() {
        presenter.detachView();
        assertFalse(presenter.isViewAttached());
    }

    /**
     * Tests the isViewAttached method  by calling it and verify if the returned value is false
     */
    @Test
    public void isViewAttached() {
        assertFalse(presenter.isViewAttached());
    }

}