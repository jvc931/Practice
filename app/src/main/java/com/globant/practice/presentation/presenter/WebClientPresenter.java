package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.WebClientState;
import com.globant.practice.presentation.view.fragment.WebClientView;
import javax.inject.Inject;

/**
 * Manages the possible states of the WebClientFragment using a view model called WebClientState,
 * load the web page on the WebView and shows o dismiss the progress dialog on the WebClientFragment
 * Created by jonathan.vargas on 4/05/2017.
 */

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
     * @param htmlUrl    address of the web page that will be load
     * @param detailType indicates if the web page are a profile or a repository
     */
    public void getWebPage(String htmlUrl, String detailType) {
        webClientState.setHtmlUrl(htmlUrl);
        webClientState.setDetailType(detailType);
        webClientState.setLoading(true);
        if (isViewAttached()) {
            view.render(webClientState);
        }

    }

    /**
     * Indicates that the web page is completely load on the WebView, works to dismiss the progress
     * dialog
     */
    public void getWebPageCompleted() {
        webClientState.setLoading(false);
        if (isViewAttached()) {
            view.render(webClientState);
        }
    }

    /**
     * Indicates that the web page load has a error, works to dismiss the progress dialog
     */
    public void getWebPageError() {
        webClientState.setLoading(false);
        if (isViewAttached()) {
            view.render(webClientState);
        }
    }

    /**
     * Indicates that the WebClient fragments are onPause state, works to dismiss the progress dialog
     */
    public void viewOnPauseState() {
        webClientState.setLoading(false);
        if (isViewAttached()) {
            view.render(webClientState);
        }
    }
}
