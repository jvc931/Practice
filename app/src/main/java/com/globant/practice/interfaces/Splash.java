package com.globant.practice.interfaces;

/**
 * Work to decouple the view of the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */

public interface Splash {
    /**
     * Create and start the intent to change the view to HomeActivity.
     */
    void changeView();
}
