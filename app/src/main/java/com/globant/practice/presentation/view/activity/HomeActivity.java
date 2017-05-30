package com.globant.practice.presentation.view.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.globant.practice.PracticeApplication;
import com.globant.practice.R;
import com.globant.practice.presentation.presenter.HomePresenter;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsFragment;
import com.globant.practice.presentation.view.fragment.SubscriberListFragment;
import com.globant.practice.presentation.view.fragment.WebClientFragment;
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
     * Attach the view with the presenter and navigate to SubscriberListFragment
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.navigateToSubscriberListFragment();
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
     * Renders the SubscriberListFragment when is the first time that the application runs
     */
    @Override
    public void render() {
        setFragment(SubscriberListFragment.newInstance(), true);
    }

    /**
     * Receives the login of the user selected
     *
     * @param login login of the user selected
     */
    @Override
    public void subscriberSelected(String login) {
        setFragment(SubscriberDetailsFragment.newInstance(login), false);
    }

    /**
     * Change the showing fragment
     *
     * @param fragment      fragment instance
     * @param firstFragment true if is the first fragment to show else false.
     */
    private void setFragment(Fragment fragment, boolean firstFragment) {
        if (firstFragment) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, fragment).commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, fragment).addToBackStack(null).commit();
        }
    }

    /**
     * Indicates that the user wants to see the profile of the subscriber
     *
     * @param htmlUrl    url with the profile address of the subscriber
     * @param detailType Indicates the detail type of the user wants to see, in this case profile
     */
    @Override
    public void nameSelected(String htmlUrl, String detailType) {
        setFragment(WebClientFragment.newInstance(htmlUrl, detailType), false);
    }

    /**
     * Indicates that the user wants to see the repository of the subscriber
     *
     * @param htmlUrl    url with the repository address of the subscriber
     * @param detailType Indicates the detail type of the user wants to see, in this case repository
     */
    @Override
    public void repositorySelected(String htmlUrl, String detailType) {
        setFragment(WebClientFragment.newInstance(htmlUrl, detailType), false);
    }

    /**
     * Listens the back key to come back the fragment stack and informs to the presenter that the
     * application will go to background by back key pressed
     */
    @Override
    public void onBackPressed() {
        if (!isFragmentComingBack()) {
            presenter.goToBackgroundByBackKeyPressed();
            super.onBackPressed();
        }
    }

    /**
     * Listens the ActionBar back key to come back the fragment stack
     *
     * @param item MenuItem that the user makes click
     * @return true if the fragment stack come back to the before fragment
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home && isFragmentComingBack()) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Indicates if the fragment stack come back to the before fragment
     *
     * @return true if the fragment stack come back to the before fragment if not return false
     */
    private boolean isFragmentComingBack() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            return true;
        } else {
            return false;
        }
    }
}
