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
import com.globant.practice.presentation.model.SubscriberListState;
import com.globant.practice.presentation.presenter.SubscriberListPresenter;
import com.globant.practice.presentation.view.Decoration;
import com.globant.practice.presentation.view.adapter.SubscriberAdapter;
import javax.inject.Inject;

/**
 * Initializes the components of the fragment_subscriber_list UI and manages the communication
 * with the presenter.
 */
public class SubscriberListFragment extends Fragment implements SubscriberListView, SubscriberAdapter.OnUserClickListener {
    private RecyclerView subscriberRecyclerView;
    private ProgressDialog fetchUserIndicator;
    private SubscriberAdapter subscriberAdapter;
    private SubscriberListFragmentActions subscriberListFragmentActions;
    @Inject
    SubscriberListPresenter presenter;

    /**
     * Returns a new instance of the SubscriberListFragment
     *
     * @return SubscriberListFragment instance
     */
    public static SubscriberListFragment newInstance() {
        return new SubscriberListFragment();
    }

    /**
     * Returns a new instances of the view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view instance for the fragment_subscriber_list
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((PracticeApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
        return inflater.inflate(R.layout.fragment_subscriber_list, container, false);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.large_app_name);
        subscriberRecyclerView = (RecyclerView) view.findViewById(R.id.subscriberRecyclerView);
        fetchUserIndicator = new ProgressDialog(view.getContext());
        fetchUserIndicator.setMessage(getString(R.string.subscriberlist_progress_dialog));
        fetchUserIndicator.setIndeterminate(true);
        fetchUserIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        subscriberAdapter = new SubscriberAdapter(null, this);
        subscriberRecyclerView.setHasFixedSize(true);
        subscriberRecyclerView.setAdapter(subscriberAdapter);
        subscriberRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subscriberRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        subscriberRecyclerView.addItemDecoration(new Decoration(view.getContext(), Decoration.VERTICAL_LIST));
    }

    /**
     * Attach the view to the presenter and fetch the subscriber list
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.fetchUsers();
    }

    /**
     * Initializes the context and the SubscriberListFragmentActions instance
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SubscriberListFragmentActions) {
            subscriberListFragmentActions = (SubscriberListFragmentActions) getActivity();
        }
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
     * @param subscriberListState State of the view
     */
    @Override
    public void render(@NonNull SubscriberListState subscriberListState) {
        if (subscriberListState.isLoading()) {
            fetchUserIndicator.show();
        } else if (subscriberListState.getUsers() != null) {
            subscriberAdapter.setUsers(subscriberListState.getUsers());
            fetchUserIndicator.dismiss();
        } else if (subscriberListState.getError() != null) {
            fetchUserIndicator.dismiss();
            showErrorMessage(subscriberListState.getError());
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
        fetchUserIndicator.dismiss();
        new AlertDialog.Builder(getView().getContext()).setTitle(message)
                .setCancelable(false).setPositiveButton(getString(R.string.accept_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }

    /**
     * Manages the user click on the subscriberRecyclerView
     *
     * @param login login of the user that the user makes click
     */
    @Override
    public void onUserClick(String login) {
        if (subscriberListFragmentActions != null) {
            subscriberListFragmentActions.subscriberSelected(login);
        }
    }

    /**
     * Works to communicate the Fragment with the Activity
     */
    public interface SubscriberListFragmentActions {
        void subscriberSelected(String login);
    }
}
