package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.FetchUsers;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.model.HomeViewState;
import com.globant.practice.presentation.view.activity.HomeView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Contains the necessary methods for the view actions.
 * Created by jonathan.vargas on 4/04/2017.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private FetchUsers interactor;
    private Observable<List<User>> getUsersListObservable;
    private List<User> userList;
    private HomeViewState homeViewState;

    /**
     * Construct method of the HomePresenter, receives a FetchUsers instance for
     * manage the user data and HomeViewState instance for manage the states of the view.
     *
     * @param interactor
     */
    @Inject
    public HomePresenter(FetchUsers interactor, HomeViewState homeViewState) {
        this.interactor = interactor;
        this.homeViewState = homeViewState;
    }

    /**
     * Detach the instance of the view and stops the ProgressDialog if is showing.
     */
    @Override
    public void detachView() {
        if (homeViewState.isLoading()) {
            homeViewState.setLoading(false);
            homeViewState.setUsers(null);
            homeViewState.setError(false);
            homeViewState.setViewShowing(false);
            view.render(homeViewState);
        }
        super.detachView();
    }

    /**
     * Makes the call for obtain a list of the users.
     */
    public void fetchUsers() {
        homeViewState.setViewShowing(true);
        if (userList == null && !homeViewState.isLoading()) {
            homeViewState.setLoading(true);
            homeViewState.setUsers(null);
            homeViewState.setError(false);
            view.render(homeViewState);
            getUsersListObservable = interactor.execute();
            getUsersListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUsersListSubscriber);
        } else {
            homeViewState.setLoading(false);
            homeViewState.setUsers(userList);
            homeViewState.setError(false);
            if (homeViewState.isViewShowing()) {
                view.render(homeViewState);
            }
        }
    }

    /**
     * Manages the callback of the execute call.
     */
    private Observer<List<User>> getUsersListSubscriber = new Observer<List<User>>() {
        @Override
        public void onError(Throwable e) {
            homeViewState.setLoading(false);
            homeViewState.setUsers(null);
            homeViewState.setError(true);
            if (homeViewState.isViewShowing()) {
                view.render(homeViewState);
            }
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(List<User> usersList) {
            userList = usersList;
            homeViewState.setLoading(false);
            homeViewState.setUsers(usersList);
            homeViewState.setError(false);
            if (homeViewState.isViewShowing()) {
                view.render(homeViewState);
            }
        }
    };
}
