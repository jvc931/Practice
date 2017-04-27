package com.globant.practice.presentation.model;

import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import java.util.List;

/**
 * Manages the states of the view
 * TODO create a new profile and repository model into presentation model to decouple the domain model to the presentation model
 * Created by jonathan.vargas on 26/04/2017.
 */

public class SubscriberDetailsState {
    private String error;
    private Profile profile;
    private List<Repository> subscriberRepositories;
    private boolean loading;
    private String login;

    /**
     * Sets the error message
     *
     * @param error error message
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Sets the subscriber profile
     *
     * @param profile subscriber profile instance
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Sets the subscriber repositories list
     *
     * @param subscriberRepositories subscriberRepositories instance
     */
    public void setSubscriberRepositories(List<Repository> subscriberRepositories) {
        this.subscriberRepositories = subscriberRepositories;
    }

    /**
     * Sets the boolean loading
     *
     * @param loading new value of the boolean
     */
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    /**
     * Sets the subscriber login
     *
     * @param login subscriber login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the error message
     *
     * @return error message
     */
    public String getError() {
        return error;
    }

    /**
     * Returns the subscriber profile
     *
     * @return subscriber profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Returns a list of the subscriber repositories
     *
     * @return list of the subscriber repositories
     */
    public List<Repository> getSubscriberRepositories() {
        return subscriberRepositories;
    }

    /**
     * Returns true if the ProgressDialog is showing
     *
     * @return true if the ProgressDialog is showing else returns false
     */
    public boolean isLoading() {
        return loading;
    }

    /**
     * Returns the subscriber login
     *
     * @return subscriber login
     */
    public String getLogin() {
        return login;
    }
}
