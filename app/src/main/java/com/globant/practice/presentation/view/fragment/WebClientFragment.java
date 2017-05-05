package com.globant.practice.presentation.view.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    private ProgressDialog webPageLoadingIndicator;
    @Inject
    WebClientPresenter presenter;

    /**
     * Manages the states of the web page load process.
     */
    private WebViewClient webViewLoadState = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            presenter.webPageLoadComplete();
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            presenter.webPageError(failingUrl);
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
        webPageLoadingIndicator = new ProgressDialog(view.getContext());
        webPageLoadingIndicator.setIndeterminate(true);
        webPageLoadingIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    /**
     * Attach the view on the presenter and initializes the web page load process
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.loadWebPage(getArguments().getString(HTML_URL_KEY), getArguments().getString(DETAIL_TYPE_KEY));
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
            webPageLoadingIndicator.setMessage(String.format(getString(R.string.web_client_progress_dialog_message), webClientState.getDetailType()));
            webClientWebView.setWebViewClient(webViewLoadState);
            webClientWebView.loadUrl(webClientState.getHtmlUrl());
            webPageLoadingIndicator.show();
        } else if (!TextUtils.isEmpty(webClientState.getError()) && webClientState.isErrorShowing()) {
            webPageLoadingIndicator.dismiss();
            showErrorMessage(webClientState.getError());
        } else {
            webPageLoadingIndicator.dismiss();
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

    /**
     * Gets the error message text from the resource file
     *
     * @return Error message text
     */
    @Override
    public String getErrorMessageText() {
        return getString(R.string.net_error_message);
    }

    /**
     * Shows a AlertDialog to inform at the user that the api call have an error
     */
    private void showErrorMessage(String message) {
        new AlertDialog.Builder(getView().getContext()).setTitle(message)
                .setCancelable(false).setPositiveButton(getString(R.string.accept_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }
}
