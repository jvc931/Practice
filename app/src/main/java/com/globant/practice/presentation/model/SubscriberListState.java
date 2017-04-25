package com.globant.practice.presentation.model;

import com.globant.practice.domain.model.User;
import java.util.List;

/**
 * Manages the states of the view
 * TODO create a new User model into presentation model to decouple the domain model to the presentation model
 * Created by jonathan.vargas on 18/04/2017.
 */

public class SubscriberListState {
    private boolean loading;
    private List<User> users;
    private String error;

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
     * Returns the error message
     *
     * @return Error message
     */
    public String getError() {
        return error;
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
     * Sets the error String
     *
     * @param error New value of the error String
     */
    public void setError(String error) {
        this.error = error;
    }
}
