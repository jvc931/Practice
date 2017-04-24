package com.globant.practice.presentation.view.fragment;

import com.globant.practice.presentation.model.SubscriberListState;
import com.globant.practice.presentation.view.BaseView;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 24/04/2017.
 */

public interface SubscriberListView extends BaseView {
    /**
     * Renders the view items depending of the view state
     *
     * @param subscriberListState State of the view
     */
    void render(SubscriberListState subscriberListState);

    /**
     * Gets the error message text from the resource file
     *
     * @return Error message text
     */
    String getErrorMessageText();
}
