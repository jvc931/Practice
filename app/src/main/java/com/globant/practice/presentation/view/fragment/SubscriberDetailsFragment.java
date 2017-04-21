package com.globant.practice.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.globant.practice.R;
import com.globant.practice.domain.model.User;

/**
 * Initialize the components of the fragment and manage the communication with the presenter
 */
public class SubscriberDetailsFragment extends Fragment {
    private User user;

    /**
     * Returns a new instance of the SubscriberDetailsFragment and sets the user instance
     *
     * @param user user instance
     * @return new SubscriberDetailsFragment instance
     */
    public static SubscriberDetailsFragment newInstance(User user) {
        SubscriberDetailsFragment subscriberDetailsFragment = new SubscriberDetailsFragment();
        subscriberDetailsFragment.setUser(user);
        return subscriberDetailsFragment;
    }

    /**
     * Initialize the ui components
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (user != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(user.getLogin());
        }
        return inflater.inflate(R.layout.fragment_subscriber_details, container, false);
    }

    /**
     * Sets the user instance
     *
     * @param user user instance
     */
    private void setUser(User user) {
        this.user = user;
    }
}
