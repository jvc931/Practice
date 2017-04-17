package com.globant.practice.presentation.view.activity;

import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.view.BaseView;
import java.util.List;

/**
 * Works to decouple the view of the presenter.
 * Created by jonathan.vargas on 5/04/2017.
 */

public interface HomeView extends BaseView {
    /**
     * Initializes the RecyclerView when have a response of the api call with the list of users
     *
     * @param users List of users
     */
    void createListHomeRecyclerView(List<User> users);

    /**
     * Shows a AlertDialog to inform at the user that the api call have an error
     */
    void showMessageError();
}
