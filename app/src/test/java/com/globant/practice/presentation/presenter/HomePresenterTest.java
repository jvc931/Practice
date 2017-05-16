package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.activity.HomeView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for the HomePresenter methods
 * Created by jonathan.vargas on 15/05/2017.
 */
public class HomePresenterTest {
    @Mock
    private HomePresenter presenter;
    @Mock
    private HomeView mockView;

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
     * Tests the navigateToSubscriberListFragment method by calling it that and verified if the method
     * runs one time.
     */
    @Test
    public void navigateToSubscriberListFragment_ShouldRun() {
        when(presenter.isViewAttached()).thenReturn(true);
        presenter.navigateToSubscriberListFragment();
        verify(presenter).navigateToSubscriberListFragment();
    }
}