package com.globant.practice.presentation.model;

import android.app.Fragment;

/**
 * Manages the states of the view
 * Created by jonathan.vargas on 24/04/2017.
 */

public class HomeViewState {
    private Fragment fragment;
    private boolean firstTimeToRun;

    /**
     * Gets the fragment instance
     *
     * @return fragment instance
     */
    public Fragment getFragment() {
        return fragment;
    }

    /**
     * Indicates if  is the first time that the application run
     *
     * @return true if is the first time if not false
     */
    public boolean isFirstTimeToRun() {
        return firstTimeToRun;
    }

    /**
     * Sets the fragment instance
     *
     * @param fragment fragment instance
     */
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
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
