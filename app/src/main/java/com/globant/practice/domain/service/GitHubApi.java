package com.globant.practice.domain.service;

import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import com.globant.practice.domain.model.User;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Interface that is required by retrofit and contains all the possible api calls that the
 * application need.
 * Created by jonathan.vargas on 4/04/2017.
 */

public interface GitHubApi {

    /**
     * Api call that returns a list of the Github users.
     *
     * @return
     */
    @GET("repos/{login}/{repo}/subscribers")
    Observable<List<User>> getUsers(@Path("login") String login, @Path("repo") String repo);

    /**
     * Api call that returns the information of the user profile, receive
     * the nickname of the user.
     *
     * @param login nickname of the user
     * @return
     */
    @GET("users/{login}")
    Observable<Profile> getUserProfile(@Path("login") String login);

    /**
     * Api call that returns a list of the repositories of the user, receive
     * the nickname of the user.
     *
     * @param login nickname of the user
     * @return
     */
    @GET("users/{login}/repos")
    Observable<List<Repository>> getUserRepositories(@Path("login") String login);
}
