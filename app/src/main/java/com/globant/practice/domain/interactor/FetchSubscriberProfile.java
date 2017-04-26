package com.globant.practice.domain.interactor;

import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.service.GitHubApi;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Provides all the methods necessary to manage the subscriber profile information.
 * Created by jonathan.vargas on 26/04/2017.
 */

public class FetchSubscriberProfile {
    private GitHubApi apiClient;

    /**
     * Construct method of FetchSubscriberProfile that receives a GithubApi reference.
     *
     * @param apiClient GitHubApi reference
     */
    @Inject
    public FetchSubscriberProfile(GitHubApi apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Returns a Observable of the apiClient call using retrofit.
     *
     * @param login login of the subscriber
     * @return Observable of the apiClient
     */
    public Observable<Profile> execute(String login) {
        return apiClient.getUserProfile(login);
    }
}
