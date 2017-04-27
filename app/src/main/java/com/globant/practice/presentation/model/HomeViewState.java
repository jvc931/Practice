package com.globant.practice.presentation.model;

import android.app.Fragment;

/**
 * Manages the states of the view
 * Created by jonathan.vargas on 24/04/2017.
 */

public class HomeViewState {
    private Fragment fragment;

    /**
     * Gets the fragment instance
     *
     * @return fragment instance
     */
    public Fragment getFragment() {
        return fragment;
    }

    /**
     * Sets the fragment instance
     *
     * @param fragment fragment instance
     */
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
