package com.globant.practice.presentation.model;

/**
 * Manages the states of the view
 * Created by jonathan.vargas on 24/04/2017.
 */

public class HomeViewState {
    private boolean firstTimeToRun;

    /**
     * Indicates if  is the first time that the application run
     *
     * @return true if is the first time if not false
     */
    public boolean isFirstTimeToRun() {
        return firstTimeToRun;
    }

    /**
     * Sets the new value of firstTimeToRun
     *
     * @param firstTimeToRun Indicates if is the first time that the application run
     */
    public void setFirstTimeToRun(boolean firstTimeToRun) {
        this.firstTimeToRun = firstTimeToRun;
    }
}
