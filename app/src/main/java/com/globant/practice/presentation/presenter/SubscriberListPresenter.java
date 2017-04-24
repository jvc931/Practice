package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.FetchUsers;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.model.SubscriberListState;
import com.globant.practice.presentation.view.fragment.SubscriberListView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Contains the methods that the SubscriberListFragment needs.
 * Created by jonathan.vargas on 24/04/2017.
 */

public class SubscriberListPresenter extends BasePresenter<SubscriberListView> {
    private FetchUsers interactor;
    private Observable<List<User>> getUsersListObservable;
    private List<User> userList;
    private SubscriberListState subscriberListState;

    /**
     * Construct method of the SubscriberListPresenter, receives a FetchUsers instance for
     * manage the user data and SubscriberListState instance for manage the states of the view.
     *
     * @param interactor
     */
    @Inject
    public SubscriberListPresenter(FetchUsers interactor) {
        this.interactor = interactor;
        subscriberListState = new SubscriberListState();
    }

    /**
     * Makes the call for obtain a list of the users.
     */
    public void fetchUsers() {
        subscriberListState.setError(null);
        if (userList == null && !subscriberListState.isLoading()) {
            subscriberListState.setLoading(true);
            subscriberListState.setUsers(null);
            if (isViewAttached()) {
                view.render(subscriberListState);
            }
            getUsersListObservable = interactor.execute();
            getUsersListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUsersListSubscriber);
        } else if (userList != null) {
            subscriberListState.setLoading(false);
            subscriberListState.setUsers(userList);
            if (isViewAttached()) {
                view.render(subscriberListState);
            }
        }
    }

    /**
     * Manages the callback of the execute call.
     */
    private Observer<List<User>> getUsersListSubscriber = new Observer<List<User>>() {
        @Override
        public void onError(Throwable e) {
            subscriberListState.setLoading(false);
            subscriberListState.setUsers(null);
            subscriberListState.setError(view.getErrorMessageText());
            if (isViewAttached()) {
                view.render(subscriberListState);
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
            subscriberListState.setLoading(false);
            subscriberListState.setUsers(usersList);
            subscriberListState.setError(null);
            if (isViewAttached()) {
                view.render(subscriberListState);
            }
        }
    };
}
