package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.activity.HomeView;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import com.globant.practice.presentation.view.fragment.SubscriberListFragment;
import com.globant.practice.presentation.view.fragment.WebClientFragment;
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
     * Renders the SubscriberListFragment on the view
     */
    public void navigateToSubscriberListFragment() {
        if (homeViewState.isFirstTimeToRun()) {
            view.render(homeViewState);
        }
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

    /**
     * Creates a new instance of WebClientFragment and render it on the view
     *
     * @param htmlUrl    url of the web page that will be load on the WebView
     * @param detailType Indicates if the web page load are a repository or a profile
     */
    public void detailSelected(String htmlUrl, String detailType) {
        homeViewState.setFragment(WebClientFragment.newInstance(htmlUrl, detailType));
        if (isViewAttached()) {
            view.render(homeViewState);
        }
    }

    /**
     * Sets the value of the firstTimeToRun boolean
     *
     * @param firstTimeToRun new value of the firstTimeToRun boolean
     */
    public void setFirstTimeToRun(boolean firstTimeToRun) {
        homeViewState.setFirstTimeToRun(firstTimeToRun);
    }
}
