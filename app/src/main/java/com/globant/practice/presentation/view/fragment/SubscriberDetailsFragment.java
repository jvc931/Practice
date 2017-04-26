package com.globant.practice.presentation.view.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.model.SubscriberDetailsState;
import com.globant.practice.presentation.presenter.SubscriberDetailsPresenter;

import javax.inject.Inject;

/**
 * Initialize the components of the fragment and manage the communication with the presenter
 */
public class SubscriberDetailsFragment extends Fragment implements SubscriberDetailsView {
    private String login;
    private static final String LOGIN_KEY = "login";
    private ProgressDialog fetchSubscriberDetailsIndicator;

    @Inject
    SubscriberDetailsPresenter presenter;

    /**
     * Returns a new instance of the SubscriberDetailsFragment and sets the login string
     *
     * @param login user login
     * @return new SubscriberDetailsFragment instance
     */
    public static SubscriberDetailsFragment newInstance(String login) {
        SubscriberDetailsFragment subscriberDetailsFragment = new SubscriberDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_KEY, login);
        subscriberDetailsFragment.setArguments(bundle);
        return subscriberDetailsFragment;
    }

    /**
     * Initialize the ui components
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
        return inflater.inflate(R.layout.fragment_subscriber_details, container, false);
    }

    /**
     * Initializes the view items
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = getArguments().getString(LOGIN_KEY);
        if (!login.isEmpty()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(login);
        }
        fetchSubscriberDetailsIndicator = new ProgressDialog(view.getContext());
        fetchSubscriberDetailsIndicator.setMessage(getString(R.string.subscriberdetails_progress_dialog));
        fetchSubscriberDetailsIndicator.setIndeterminate(true);
        fetchSubscriberDetailsIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    /**
     * Attach the view to the presenter and fetch the subscriber details
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.fetchSubscriberDetails(login);
    }

    /**
     * Detach the view to the presenter
     */
    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }

    /**
     * Renders the view items depending of the view state
     *
     * @param subscriberDetailsState State of the view
     */
    @Override
    public void render(@NonNull SubscriberDetailsState subscriberDetailsState) {
        if (subscriberDetailsState.isLoading()) {
            fetchSubscriberDetailsIndicator.show();
        } else if (subscriberDetailsState.getProfile() != null && subscriberDetailsState.getSubscriberRepositories() != null) {
        } else if (subscriberDetailsState.getError() != null) {
            fetchSubscriberDetailsIndicator.dismiss();
            showErrorMessage(subscriberDetailsState.getError());
        }
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
