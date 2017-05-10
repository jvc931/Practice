package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.WebClientState;
import com.globant.practice.presentation.view.fragment.WebClientView;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages the possible states of the WebClientFragment using a view model called WebClientState,
 * load the web page on the WebView and shows o dismiss the progress dialog on the WebClientFragment
 * Created by jonathan.vargas on 4/05/2017.
 */
@Singleton
public class WebClientPresenter extends BasePresenter<WebClientView> {
    private WebClientState webClientState;

    /**
     * Construct method of the WebClientPresenter and initializes the WebClientState model
     */
    @Inject
    public WebClientPresenter() {
        webClientState = new WebClientState();
    }

    /**
     * Initializes the web page load progress
     *
     * @param htmlUrl address of the web page that will be load
     */
    public void loadWebPage(String htmlUrl) {
        if (!webClientState.isLoading()) {
            webClientState.setHtmlUrl(htmlUrl);
            webClientState.setLoading(true);
            webClientState.setErrorShowing(false);
            webClientState.setError(null);
        }
        if (isViewAttached()) {
            view.render(webClientState);
        }
    }

    /**
     * Indicates that the web page is completely load on the WebView, works to dismiss the progress
     * dialog
     */
    public void webPageLoadComplete() {
        webClientState.setLoading(false);
        webClientState.setErrorShowing(false);
        webClientState.setError(null);
        if (isViewAttached()) {
            view.render(webClientState);
        }
    }

    /**
     * Indicates that the web page load has a error, works to dismiss the progress dialog
     */
    public void webPageError(String failingUrl) {
        if (failingUrl.equals(webClientState.getHtmlUrl()) && !webClientState.isErrorShowing()) {
            webClientState.setLoading(false);
            webClientState.setErrorShowing(true);
            if (isViewAttached()) {
                webClientState.setError(view.getErrorMessageText());
                view.render(webClientState);
            }
        }
    }
}
