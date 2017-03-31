package com.globant.practice.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.practice.R;

/**
 * Initialize the components of the activity_splash UI and manage the communication
 * with the presenter.
 * Created by jonathan.vargas on 31/03/2017.
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * Initialize the UI components and the presenter instance.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
