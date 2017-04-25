package com.globant.practice.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.globant.practice.R;

/**
 * Initialize the components of the fragment and manage the communication with the presenter
 */
public class SubscriberDetailsFragment extends Fragment {
    private String login;
    private static final String LOGIN_KEY = "login";

    /**
     * Returns a new instance of the SubscriberDetailsFragment and sets the login string
     *
     * @param login user login
     * @return new SubscriberDetailsFragment instance
     */
    public static SubscriberDetailsFragment newInstance(String login) {
        SubscriberDetailsFragment subscriberDetailsFragment = new SubscriberDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_KEY, login);
        subscriberDetailsFragment.setArguments(bundle);
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
        return inflater.inflate(R.layout.fragment_subscriber_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = getArguments().getString(LOGIN_KEY);
        if (login != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(login);
        }
    }
}
