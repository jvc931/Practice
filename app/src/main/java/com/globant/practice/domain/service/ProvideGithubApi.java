package com.globant.practice.domain.service;

import com.globant.practice.BuildConfig;
import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import com.globant.practice.domain.model.User;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides the initialisation of retrofit and manages all the possible Api calls
 * that the application needs.
 * Created by jonathan.vargas on 5/04/2017.
 */

public class ProvideGithubApi {

    private Retrofit retrofit;
    private ApiGitHub apiGitHub;

    /**
     * Construct method that initializes retrofit.
     */
    @Inject
    public ProvideGithubApi() {
        retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        apiGitHub = retrofit.create(ApiGitHub.class);
    }

    /**
     * Returns the Api call that get the list of users.
     *
     * @return
     */
    public Observable<List<User>> getUsers() {
        return apiGitHub.getUsers();
    }

    /**
     * Returns the Api call that get the profile information of the user, receives the
     * nickname of the user.
     *
     * @param login nickname of the user
     * @return
     */
    public Observable<Profile> getUserProfile(String login) {
        return apiGitHub.getUserProfile(login);
    }

    /**
     * Returns the Api call that get the list of the user repositories, receives the
     * nickname of the user.
     *
     * @param login nickname of the user
     * @return
     */
    public Observable<List<Repository>> getUserRepositories(String login) {
        return apiGitHub.getUserRepositories(login);
    }
}
