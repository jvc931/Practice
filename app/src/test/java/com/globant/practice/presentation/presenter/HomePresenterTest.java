package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.activity.HomeView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit test for the HomePresenter methods
 * Created by jonathan.vargas on 15/05/2017.
 */
public class HomePresenterTest {
    @Mock(name = "homeViewState")
    private HomeViewState mockHomeViewState;
    @InjectMocks
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
     * Tests the navigateToSubscriberListFragment method by calling it that and verified if the
     * render method is called.
     */
    @Test
    public void fetchSubscriberList_withViewAttached_shouldShowSubscribers() {
        when(mockHomeViewState.isFirstTimeToRun()).thenReturn(true);
        presenter.navigateToSubscriberListFragment();
        verify(mockView).render();
    }

    /**
     * Tests the navigateToSubscriberListFragment method by calling it that and verified if the
     * render method is not called.
     */
    @Test
    public void fetchSubscriberList_withViewDetached_shouldNotShowSubscribers() {
        presenter.detachView();
        when(mockHomeViewState.isFirstTimeToRun()).thenReturn(true);
        presenter.navigateToSubscriberListFragment();
        verify(mockView, never()).render();
    }
}