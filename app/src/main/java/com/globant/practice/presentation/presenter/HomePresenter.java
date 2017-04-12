package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.ObtainUsers;
import com.globant.practice.domain.model.User;
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

    private ObtainUsers interactor;
    private Observable<List<User>> getUsersListObservable;
    private List<User> userList;

    /**
     * Construct method of the HomePresenter, receives a ObtainUsers instance for
     * manage the user data.
     *
     * @param interactor
     */
    @Inject
    public HomePresenter(ObtainUsers interactor) {
        this.interactor = interactor;
    }

    /**
     * Makes the call for obtain a list of the users.
     */
    public void fetchUsers() {
        if (userList == null) {
            getUsersListObservable = interactor.fetchUsers();
            getUsersListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUsersListSubscriber);
        }
    }

    /**
     * Manages the callback of the fetchUsers call.
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
            userList = usersList;
        }
    };
}
