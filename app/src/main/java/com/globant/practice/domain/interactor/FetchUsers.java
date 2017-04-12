package com.globant.practice.domain.interactor;

import com.globant.practice.domain.model.User;
import com.globant.practice.domain.service.GitHubApi;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Provides all the methods necessary to manage the user information.
 * Created by jonathan.vargas on 4/04/2017.
 */

public class FetchUsers {

    private GitHubApi apiClient;
    private static final String LOGIN = "googlesamples";
    private static final String REPO = "android-architecture";

    /**
     * Construct method of FetchUsers that receives a GithubApi reference.
     *
     * @param apiClient GitHubApi reference
     */
    @Inject
    public FetchUsers(GitHubApi apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Returns a Observable of the apiClient call using retrofit.
     *
     * @return
     */
    public Observable<List<User>> execute() {
        return apiClient.getRepoSubscribers(LOGIN, REPO);
    }
}
