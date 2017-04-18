package com.globant.practice.presentation.view.activity;

import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.BaseView;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 5/04/2017.
 */

public interface HomeView extends BaseView {
    /**
     * Renders the view items depending of the view state
     *
     * @param homeViewState State of the view
     */
    void render(HomeViewState homeViewState);
}
