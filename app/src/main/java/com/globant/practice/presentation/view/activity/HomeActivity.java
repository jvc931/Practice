package com.globant.practice.presentation.view.activity;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import com.globant.practice.presentation.view.fragment.SubscriberListFragment;
import javax.inject.Inject;

/**
 * Initializes the components of the activity_home UI and manages the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity implements HomeView, SubscriberListFragment.SubscriberListFragmentActions, SubscriberDetailsFragment.SubscriberDetailsActions {

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
     * Attach the view with the presenter.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
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
     * @param homeViewState state of the view
     */
    @Override
    public void render(@NonNull HomeViewState homeViewState) {
        setFragment(homeViewState.getFragment());
    }

    /**
     * Receives the login of the user selected
     *
     * @param login login of the user selected
     */
    @Override
    public void subscriberSelected(String login) {
        presenter.subscriberSelected(login);
    }

    /**
     * Change the fragment
     *
     * @param fragment fragment instance
     */
    private void setFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commit();
    }

    /**
     * Indicates that the user wants to see the profile of the subscriber
     *
     * @param htmlUrl    url with the profile address of the subscriber
     * @param detailType Indicates the detail type of the user wants to see, in this case profile
     */
    @Override
    public void nameSelected(String htmlUrl, String detailType) {
        presenter.detailSelected(htmlUrl, detailType);
    }

    /**
     * Indicates that the user wants to see the repository of the subscriber
     *
     * @param htmlUrl    url with the repository address of the subscriber
     * @param detailType Indicates the detail type of the user wants to see, in this case repository
     */
    @Override
    public void repositorySelected(String htmlUrl, String detailType) {
        presenter.detailSelected(htmlUrl, detailType);
    }
}
