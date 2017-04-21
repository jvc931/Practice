package com.globant.practice.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.view.Decoration;
import com.globant.practice.presentation.view.adapter.SubscriberAdapter;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import javax.inject.Inject;

/**
 * Initializes the components of the activity_home UI and manages the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity implements HomeView, SubscriberAdapter.OnUserClickListener {

    private RecyclerView subscriberRecyclerView;
    private ProgressDialog fetchUserIndicator;
    private SubscriberAdapter subscriberAdapter;
    private LinearLayout subscriberDetailsLayout, subscriberRecyclerViewLayout;
    @Inject
    HomePresenter presenter;

    /**
     * Initializes the UI components and the presenter instance.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ((PracticeApplication) getApplication()).getApplicationComponent().inject(this);
        getSupportActionBar().setTitle(getString(R.string.large_app_name));
        subscriberRecyclerView = (RecyclerView) findViewById(R.id.subscriberRecyclerView);
        fetchUserIndicator = new ProgressDialog(this);
        fetchUserIndicator.setMessage(getString(R.string.home_progress_dialog));
        fetchUserIndicator.setIndeterminate(true);
        fetchUserIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        subscriberAdapter = new SubscriberAdapter(null, this);
        subscriberRecyclerView.setHasFixedSize(true);
        subscriberRecyclerView.setAdapter(subscriberAdapter);
        subscriberRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subscriberRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        subscriberRecyclerView.addItemDecoration(new Decoration(this, Decoration.VERTICAL_LIST));
        subscriberDetailsLayout = (LinearLayout) findViewById(R.id.subscriberDetailsLayout);
        subscriberRecyclerViewLayout = (LinearLayout) findViewById(R.id.subscriberRecyclerViewLayout);
    }

    /**
     * Initializes the Api call that obtain the user list.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.fetchUsers();
    }

    /**
     * Informs the presenter that have to detach de View
     */
    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    /**
     * Renders the view items depending of the view state
     *
     * @param homeViewState State of the view
     */
    @Override
    public void render(@NonNull HomeViewState homeViewState) {
        if (homeViewState.isLoading()) {
            fetchUserIndicator.show();
        } else if (homeViewState.getUsers() != null) {
            subscriberAdapter.setUsers(homeViewState.getUsers());
            fetchUserIndicator.dismiss();
        } else if (homeViewState.getError() != null) {
            fetchUserIndicator.dismiss();
            showErrorMessage(homeViewState.getError());
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
     * Charge the SubscriberDetailsFragment into the HomeActivity
     *
     * @param user User instance of the user selected
     */
    @Override
    public void navigateToSubscriberDetails(User user) {
        getFragmentManager().beginTransaction()
                .replace(R.id.subscriberDetailsLayout, SubscriberDetailsFragment.newInstance(user)).commit();
        subscriberRecyclerViewLayout.setVisibility(View.GONE);
        subscriberDetailsLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Shows a AlertDialog to inform at the user that the api call have an error
     */
    private void showErrorMessage(String message) {
        fetchUserIndicator.dismiss();
        new AlertDialog.Builder(this).setTitle(message)
                .setCancelable(false).setPositiveButton(getString(R.string.accept_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }

    /**
     * Manages the user clicks
     *
     * @param user User instance of the object that the user makes click
     */
    @Override
    public void onUserClick(User user) {
        presenter.userClick(user);
    }
}
