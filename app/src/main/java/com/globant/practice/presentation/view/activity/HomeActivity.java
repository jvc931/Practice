package com.globant.practice.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.presenter.HomePresenter;
import javax.inject.Inject;

/**
 * Initializes the components of the activity_home UI and manages the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity implements HomeView {

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
}
