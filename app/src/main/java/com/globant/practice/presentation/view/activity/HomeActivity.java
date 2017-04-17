package com.globant.practice.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.view.Decoration;
import com.globant.practice.presentation.view.adapter.ListHomeAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * Initializes the components of the activity_home UI and manages the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity implements HomeView {

    private RecyclerView listHomeRecyclerView;
    private ProgressDialog fetchUserIndicator;
    private ListHomeAdapter listHomeAdapter;
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
        fetchUserIndicator.show();
    }

    /**
     * Informs the presenter that have to detach de View
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (fetchUserIndicator.isShowing()) {
            fetchUserIndicator.dismiss();
        }
        presenter.detachView();
    }

    /**
     * Initializes the RecyclerView when have a response of the api call with the list of users
     *
     * @param users List of users
     */
    @Override
    public void createListHomeRecyclerView(List<User> users) {
        listHomeRecyclerView.setHasFixedSize(true);
        listHomeAdapter = new ListHomeAdapter(users);
        listHomeRecyclerView.setAdapter(listHomeAdapter);
        listHomeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        listHomeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listHomeRecyclerView.addItemDecoration(new Decoration(this, Decoration.VERTICAL_LIST));
        listHomeAdapter.setOnClickListener(listHomeOnClickListener);
        fetchUserIndicator.dismiss();
    }

    /**
     * Shows a AlertDialog to inform at the user that the api call have an error
     */
    @Override
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

    /**
     * Manages the actions when the user makes a click
     */
    private View.OnClickListener listHomeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
}
