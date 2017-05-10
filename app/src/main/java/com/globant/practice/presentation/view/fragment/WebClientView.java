package com.globant.practice.presentation.view.fragment;

import com.globant.practice.presentation.model.WebClientState;
import com.globant.practice.presentation.view.BaseView;

/**
 * Manages the actions between WebClientPresenter and WebClientFragment
 * Created by jonathan.vargas on 4/05/2017.
 */

public interface WebClientView extends BaseView {
    /**
     * Renders the view items depending of the WebClientState
     *
     * @param webClientState instance of the WebClientState
     */
    void render(WebClientState webClientState);

    /**
     * Gets the error message text from the resource file
     *
     * @return Error message text
     */
    String getErrorMessageText();
}
