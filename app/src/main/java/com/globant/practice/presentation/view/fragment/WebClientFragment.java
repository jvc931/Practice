package com.globant.practice.presentation.view.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.model.WebClientState;
import com.globant.practice.presentation.presenter.WebClientPresenter;
import javax.inject.Inject;

/**
 * Initializes and manage the communication with the presenter, initializes and manage the view items,
 * load the web page and manages the states of the load process.
 */
public class WebClientFragment extends Fragment implements WebClientView {
    private static final String HTML_URL_KEY = "url";
    private static final String DETAIL_TYPE_KEY = "type";
    private WebView webClientWebView;
    private ProgressDialog getWebPageIndicator;
    @Inject
    WebClientPresenter presenter;

    /**
     * Manages the states of the web page load process.
     */
    private WebViewClient webViewLoadState = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            presenter.getWebPageCompleted();
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            presenter.getWebPageError();
        }

        @TargetApi(android.os.Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            onReceivedError(view, error.getErrorCode(), error.getDescription().toString(), request.getUrl().toString());
        }
    };

    /**
     * Returns a new instance if WebClientFragment with the necessary arguments that the fragment needs
     *
     * @param htmlUrl    url of the profile or repository address
     * @param detailType Indicates if the web page is a profile or a repository
     * @return new instance of WebClientFragment with the necessary arguments
     */
    public static WebClientFragment newInstance(String htmlUrl, String detailType) {
        WebClientFragment fragment = new WebClientFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HTML_URL_KEY, htmlUrl);
        bundle.putString(DETAIL_TYPE_KEY, detailType);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Inflates the fragment_web_client and initializes the WebClientPresenter
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((PracticeApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
        return inflater.inflate(R.layout.fragment_web_client, container, false);
    }

    /**
     * Initializes the view items and set the ActionBar title
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!getArguments().getString(DETAIL_TYPE_KEY).isEmpty()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString(DETAIL_TYPE_KEY));
        }
        webClientWebView = (WebView) view.findViewById(R.id.web_client_webview);
        getWebPageIndicator = new ProgressDialog(view.getContext());
        getWebPageIndicator.setIndeterminate(true);
        getWebPageIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    /**
     * Attach the view on the presenter and initializes the web page load process
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.getWebPage(getArguments().getString(HTML_URL_KEY), getArguments().getString(DETAIL_TYPE_KEY));
    }

    /**
     * Detach the view on the presenter
     */
    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }

    /**
     * Shows or dismiss the progress dialog, load the web page on the WebView depending of the
     * webClientState
     *
     * @param webClientState instance of the WebClientState
     */
    @Override
    public void render(@NonNull WebClientState webClientState) {
        if (webClientState.isLoading()) {
            getWebPageIndicator.setMessage(String.format(getString(R.string.web_client_progress_dialog_message), webClientState.getDetailType()));
            webClientWebView.setWebViewClient(webViewLoadState);
            webClientWebView.loadUrl(webClientState.getHtmlUrl());
            getWebPageIndicator.show();
        } else {
            getWebPageIndicator.dismiss();
        }
    }

    /**
     * Indicates to the presenter that the fragment is going into onPause state
     */
    @Override
    public void onPause() {
        super.onPause();
        presenter.viewOnPauseState();
    }
}
