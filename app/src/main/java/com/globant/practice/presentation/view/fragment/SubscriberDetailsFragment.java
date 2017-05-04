package com.globant.practice.presentation.view.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.model.SubscriberDetailsState;
import com.globant.practice.presentation.presenter.SubscriberDetailsPresenter;
import com.globant.practice.presentation.view.Decoration;
import com.globant.practice.presentation.view.adapter.SubscriberDetailsAdapter;
import javax.inject.Inject;

/**
 * Initialize the components of the fragment and manage the communication with the presenter
 */
public class SubscriberDetailsFragment extends Fragment implements SubscriberDetailsView, SubscriberDetailsAdapter.OnUserClickListener {
    private static final String LOGIN_KEY = "login";
    private ProgressDialog fetchSubscriberDetailsIndicator;
    private RecyclerView subscriberDetailsRecyclerView;
    private SubscriberDetailsAdapter subscriberDetailsAdapter;
    private SubscriberDetailsActions subscriberDetailsActions;
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
        if (!getArguments().getString(LOGIN_KEY).isEmpty()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString(LOGIN_KEY));
        }
        fetchSubscriberDetailsIndicator = new ProgressDialog(view.getContext());
        fetchSubscriberDetailsIndicator.setMessage(getString(R.string.subscriberdetails_progress_dialog));
        fetchSubscriberDetailsIndicator.setIndeterminate(true);
        fetchSubscriberDetailsIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        subscriberDetailsRecyclerView = (RecyclerView) view.findViewById(R.id.subscriber_details_recyclerview);
        subscriberDetailsAdapter = new SubscriberDetailsAdapter(null, null, this);
        subscriberDetailsRecyclerView.setHasFixedSize(true);
        subscriberDetailsRecyclerView.setAdapter(subscriberDetailsAdapter);
        subscriberDetailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subscriberDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        subscriberDetailsRecyclerView.addItemDecoration(new Decoration(view.getContext(), Decoration.VERTICAL_LIST));
    }

    /**
     * Initializes the context and the SubscriberDetailsActions instance
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SubscriberDetailsActions) {
            subscriberDetailsActions = (SubscriberDetailsActions) getActivity();
        }
    }

    /**
     * Attach the view to the presenter and fetch the subscriber details sending the subscriber
     * login
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.fetchSubscriberDetails(getArguments().getString(LOGIN_KEY));
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
            fetchSubscriberDetailsIndicator.dismiss();
            subscriberDetailsAdapter.setSubscriberDetails(subscriberDetailsState.getProfile(), subscriberDetailsState.getSubscriberRepositories());
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

    /**
     * Manages the actions when the user makes click on the subscriber name
     *
     * @param htmlUrl contains the url to the GitHub profile
     */
    @Override
    public void onProfileNameClick(String htmlUrl) {
        if (subscriberDetailsActions != null) {
            subscriberDetailsActions.nameSelected(htmlUrl, getString(R.string.subscriber_detail_type_profile));
        }
    }

    /**
     * Manages the actions when the users makes click on the repository item
     *
     * @param htmlUrl contains the url to the GitHub repository
     */
    @Override
    public void onRepositoryClick(String htmlUrl) {
        if (subscriberDetailsActions != null) {
            subscriberDetailsActions.repositorySelected(htmlUrl, getString(R.string.subscriber_detail_type_repository));
        }
    }

    /**
     * Manages the actions between SubscriberDetailsFragment and HomeActivity
     */
    public interface SubscriberDetailsActions {
        /**
         * Indicates that the user wants to see the profile of the subscriber
         *
         * @param htmlUrl    url with the profile address of the subscriber
         * @param detailType Indicates the detail type of the user wants to see, in this case profile
         */
        void nameSelected(String htmlUrl, String detailType);

        /**
         * Indicates that the user wants to see the repository of the subscriber
         *
         * @param htmlUrl    url with the repository address of the subscriber
         * @param detailType Indicates the detail type of the user wants to see, in this case repository
         */
        void repositorySelected(String htmlUrl, String detailType);
    }
}
