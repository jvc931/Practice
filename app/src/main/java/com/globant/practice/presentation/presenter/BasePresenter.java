package com.globant.practice.presentation.presenter;

import com.globant.practice.presentation.view.activity.BaseView;

/**
 * Contains the common methods that the presenters needs.
 * Created by jonathan.vargas on 11/04/2017.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    /**
     * Receives and assign an instance of the view interface.
     *
     * @param view instance of the view interface
     */
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * Detach the instance of the view and change the flag that indicates
     * if is the first time that the application is invoked.
     */
    public void detachView() {
        view = null;
    }
}
