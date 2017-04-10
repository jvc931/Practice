package com.globant.practice.domain.interactor;

import com.globant.practice.domain.model.User;
import com.globant.practice.domain.service.ProvideGithubApi;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides all the methods necessary to manage the user information.
 * Created by jonathan.vargas on 4/04/2017.
 */

public class UserInteractor {

    private ProvideGithubApi api;
    private List<User> usersList;
    private Observable<List<User>> getUsersListObservable;

    /**
     * Construct method of UserInteractor that receives a GithubApi reference.
     *
     * @param api
     */
    @Inject
    public UserInteractor(ProvideGithubApi api) {
        this.api = api;
    }

    /**
     * Returns a Observable reference that can be a reference of the api call o a reference of the
     * list depending if the list is null or not.
     *
     * @return
     */
    public Observable<List<User>> getUsers() {
        if (usersList == null) {
            getUsersListObservable = api.getUsers();
            getUsersListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUsersListSubscriber);

            return getUsersListObservable;
        } else {
            return getUsersListObservable.just(getUsersList());
        }
    }

    /**
     * Sets the usersList with the values obtained with the api call.
     *
     * @param usersList List of users
     */
    private void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    /**
     * returns the usersList.
     *
     * @return List of User type objects
     */
    public List<User> getUsersList() {
        return this.usersList;
    }

    /**
     * Manages the callback of the api call.
     */
    private Observer<List<User>> getUsersListSubscriber = new Observer<List<User>>() {
        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(List<User> usersList) {
            setUsersList(usersList);
        }
    };
}
