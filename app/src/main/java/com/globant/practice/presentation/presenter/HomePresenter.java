package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.activity.HomeView;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import com.globant.practice.presentation.view.fragment.SubscriberListFragment;
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
        homeViewState.setFragment(SubscriberListFragment.newInstance());
    }

    /**
     * Receives and assign an instance of the view interface and render the HomeActivity
     *
     * @param view instance of the view interface
     */
    @Override
    public void attachView(HomeView view) {
        super.attachView(view);
        view.render(homeViewState);
    }

    /**
     * Receives the login of the user selected
     *
     * @param login login of the user
     */
    public void subscriberSelected(String login) {
        homeViewState.setFragment(SubscriberDetailsFragment.newInstance(login));
        if (isViewAttached()) {
            view.render(homeViewState);
        }
    }
}
