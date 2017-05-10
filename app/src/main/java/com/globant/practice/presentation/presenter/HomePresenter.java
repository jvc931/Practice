package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.activity.HomeView;
import javax.inject.Inject;

/**
 * Contains the necessary methods for the view actions.
 * Created by jonathan.vargas on 4/04/2017.
 */

public class HomePresenter extends BasePresenter<HomeView> {
    private HomeViewState homeViewState;

    /**
     * Construct method of the HomePresenter, creates HomeViewState instance for manage the states of the view.
     */
    @Inject
    public HomePresenter() {
        homeViewState = new HomeViewState();
        homeViewState.setFirstTimeToRun(true);
    }

    /**
     * Renders the SubscriberListFragment on the view
     */
    public void navigateToSubscriberListFragment() {
        if (homeViewState.isFirstTimeToRun()) {
            view.render();
            homeViewState.setFirstTimeToRun(false);
        }
    }
}
