package com.globant.practice.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.view.Decoration;
import com.globant.practice.presentation.view.adapter.SubscriberAdapter;

import javax.inject.Inject;

/**
 * Initializes the components of the activity_home UI and manages the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity implements HomeView {

    private RecyclerView listHomeRecyclerView;
    private ProgressDialog fetchUserIndicator;
    private SubscriberAdapter listHomeAdapter;
    private HomeViewState homeViewState;
    @Inject
    HomePresenter presenter;

    /**
     * Manages the actions when the user makes click
     */
    private SubscriberAdapter.OnUserClickListener userClickListener = new SubscriberAdapter.OnUserClickListener() {
        @Override
        public void onUserClick(User item) {

        }
    };

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
        getSupportActionBar().setTitle(getString(R.string.splash_text));
        listHomeRecyclerView = (RecyclerView) findViewById(R.id.listHomeRecyclerView);
        fetchUserIndicator = new ProgressDialog(this);
        fetchUserIndicator.setMessage(getString(R.string.home_progress_dialog));
        fetchUserIndicator.setIndeterminate(true);
        fetchUserIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
    public void render(HomeViewState homeViewState) {
        this.homeViewState = homeViewState;
        if (homeViewState.isLoading()) {
            fetchUserIndicator.show();
        } else if (homeViewState.getUsers() != null) {
            listHomeRecyclerView.setHasFixedSize(true);
            listHomeAdapter = new SubscriberAdapter(homeViewState.getUsers(), userClickListener);
            listHomeRecyclerView.setAdapter(listHomeAdapter);
            listHomeRecyclerView.setItemAnimator(new DefaultItemAnimator());
            listHomeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            listHomeRecyclerView.addItemDecoration(new Decoration(this, Decoration.VERTICAL_LIST));
            fetchUserIndicator.dismiss();
        } else if (homeViewState.isError()) {
            fetchUserIndicator.dismiss();
            showMessageError();
        } else if (!homeViewState.isViewShowing()) {
            fetchUserIndicator.dismiss();
        }
    }

    /**
     * Shows a AlertDialog to inform at the user that the api call have an error
     */
    public void showMessageError() {
        fetchUserIndicator.dismiss();
        new AlertDialog.Builder(this).setTitle(getString(R.string.net_error_message))
                .setCancelable(false).setPositiveButton(getString(R.string.accept_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }
}
