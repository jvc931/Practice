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

public class UserInteractor {

    private GitHubApi apiClient;

    /**
     * Construct method of UserInteractor that receives a GithubApi reference.
     *
     * @param apiClient GitHubApi reference
     */
    @Inject
    public UserInteractor(GitHubApi apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Returns a Observable of the apiClient call using retrofit.
     *
     * @return
     */
    public Observable<List<User>> getUsers() {
        return apiClient.getUsers();
    }
}
