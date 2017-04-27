package com.globant.practice.domain.interactor;

import com.globant.practice.domain.model.Repository;
import com.globant.practice.domain.service.GitHubApi;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Provides all the methods necessary to manage the subscriber repository information.
 * Created by jonathan.vargas on 26/04/2017.
 */

public class FetchSubscriberRepositoriesInteractor {
    private GitHubApi apiClient;

    /**
     * Construct method of FetchSubscriberRepositoriesInteractor that receives a GithubApi reference.
     *
     * @param apiClient GitHubApi reference
     */
    @Inject
    public FetchSubscriberRepositoriesInteractor(GitHubApi apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Returns a Observable of the apiClient call using retrofit.
     *
     * @param login login of the subscriber
     * @return Observable of the apiClient
     */
    public Observable<List<Repository>> execute(String login) {
        return apiClient.getUserRepositories(login);
    }
}
