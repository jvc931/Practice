package com.globant.practice.presentation.view.fragment;

import com.globant.practice.presentation.model.SubscriberDetailsState;
import com.globant.practice.presentation.view.BaseView;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 26/04/2017.
 */

public interface SubscriberDetailsView extends BaseView {
    /**
     * Renders the view items depending of the view state
     *
     * @param subscriberDetailsState State of the view
     */
    void render(SubscriberDetailsState subscriberDetailsState);

    /**
     * Gets the error message text from the resource file
     *
     * @return Error message text
     */
    String getErrorMessageText();
}
