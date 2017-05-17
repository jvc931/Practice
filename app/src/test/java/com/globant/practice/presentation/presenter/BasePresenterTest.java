package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.BaseView;
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
    private BaseView mockView;
    @Mock
    private BasePresenter presenter;

    @Before
    /**
     * Initializes the presenter and the mock views.
     */
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mockView);
    }

    /**
     * Calls the attachView method using different types of views, after that verifies if the
     * method execute one time.
     */
    @Test
    public void attachView_passVerifyMethod() {
        verify(presenter).attachView(mockView);
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