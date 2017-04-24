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
    private String nickname;

    /**
     * Returns a new instance of the SubscriberDetailsFragment and sets the user instance
     *
     * @param nickname user nickname
     * @return new SubscriberDetailsFragment instance
     */
    public static SubscriberDetailsFragment newInstance(String nickname) {
        SubscriberDetailsFragment subscriberDetailsFragment = new SubscriberDetailsFragment();
        subscriberDetailsFragment.setNickname(nickname);
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
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        if (nickname != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(nickname);
        }
    }

    /**
     * Sets the user instance
     *
     * @param nickname user nickname
     */
    private void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
