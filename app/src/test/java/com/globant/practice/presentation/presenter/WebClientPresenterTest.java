package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.WebClientState;
import com.globant.practice.presentation.view.fragment.WebClientView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for the WebClientMethods
 * Created by jonathan.vargas on 19/05/2017.
 */
public class WebClientPresenterTest {
    @InjectMocks
    private WebClientPresenter presenter;
    @Mock
    private WebClientView mockView;
    @Mock(name = "webClientState")
    private WebClientState mockWebClientState;

    /**
     * Initializes the mock objects
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Unit test of loadWebPage, should show the loading indicator and initializes the load of
     * the web page because the view is attached
     *
     * @throws Exception
     */
    @Test
    public void loadWebPage_withAttachView_ShouldShowLoadingIndicatorAndLoadWebPage() throws Exception {
        presenter.attachView(mockView);
        when(mockWebClientState.isLoading()).thenReturn(false);
        presenter.loadWebPage("www.mockUrl.com");
        verify(mockView, times(1)).render(any(WebClientState.class));
    }

    /**
     * Unit test of webPageLoadComplete, should dismiss the loading indicator because the view is
     * attached
     *
     * @throws Exception
     */
    @Test
    public void webPageLoadComplete_withAttachView_ShouldDismissLoadingIndicator() throws Exception {
        presenter.attachView(mockView);
        presenter.webPageLoadComplete();
        verify(mockView, times(1)).render(any(WebClientState.class));
    }

    /**
     * Unit test of webPageError, should dismiss the loading indicator and show the error message
     * because the view is attached
     *
     * @throws Exception
     */
    @Test
    public void webPageError_witAttachView_ShouldDismissLoadingIndicatorAndShowErrorMessage() throws Exception {
        presenter.attachView(mockView);
        when(mockWebClientState.getHtmlUrl()).thenReturn("mockError");
        presenter.webPageError("mockError");
        verify(mockView, times(1)).render(any(WebClientState.class));
    }

    /**
     * Unit test of the loadWebPage method, should not show the loading indicator and didn't load
     * the web page because the view is detached
     *
     * @throws Exception
     */
    @Test
    public void loadWebPage_withDetachView_ShouldNotShowLoadingIndicator() throws Exception {
        presenter.detachView();
        when(mockWebClientState.isLoading()).thenReturn(false);
        presenter.loadWebPage("www.mockUrl.com");
        verify(mockView, never()).render(any(WebClientState.class));
    }

    /**
     * Unit test of webPageLoadComplete, should not dismiss the loading indicator because the view
     * is detached
     *
     * @throws Exception
     */
    @Test
    public void webPageLoadComplete_withDetachView_ShouldNotDismissLoadingIndicator() throws Exception {
        presenter.detachView();
        presenter.webPageLoadComplete();
        verify(mockView, never()).render(any(WebClientState.class));
    }

    /**
     * Unit test of webPageError, should not dismiss the loading indicator and didn't show the error
     * message because the view is detached
     *
     * @throws Exception
     */
    @Test
    public void webPageError_witDetachView_ShouldNotDismissLoadingIndicator() throws Exception {
        presenter.detachView();
        when(mockWebClientState.getHtmlUrl()).thenReturn("mockError");
        presenter.webPageError("mockError");
        verify(mockView, never()).render(any(WebClientState.class));
    }
}