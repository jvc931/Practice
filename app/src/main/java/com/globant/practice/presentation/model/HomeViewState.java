package com.globant.practice.presentation.model;

import com.globant.practice.domain.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 * Managas the states of the view
 * Created by jonathan.vargas on 18/04/2017.
 */

public class HomeViewState {
    private boolean loading;
    private List<User> users;
    private boolean error;
    private boolean viewShowing;

    /**
     * Construct method of the model
     */
    @Inject
    public HomeViewState() {
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
     * Returs the list of the users that are subscriber to the repository
     *
     * @return list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Returns true if the api call has a error
     *
     * @return true if the api call has a error else returns false
     */
    public boolean isError() {
        return error;
    }

    /**
     * Returns true if the view is showing to the user
     *
     * @return true if the view is showing else returns false
     */
    public boolean isViewShowing() {
        return viewShowing;
    }

    /**
     * Sets the loading boolean
     *
     * @param loading New value of the loading boolean
     */
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    /**
     * Sets the users list
     *
     * @param users New value of the users list
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Sets the error boolean
     *
     * @param error New value of the error boolean
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Sets the viewShowing boolean
     *
     * @param viewShowing New value of the viewShowing
     */
    public void setViewShowing(boolean viewShowing) {
        this.viewShowing = viewShowing;
    }
}
