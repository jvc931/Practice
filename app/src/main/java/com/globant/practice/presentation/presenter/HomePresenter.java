package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.FetchUsers;
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

    private FetchUsers interactor;
    private Observable<List<User>> getUsersListObservable;
    private List<User> userList;

    /**
     * Construct method of the HomePresenter, receives a FetchUsers instance for
     * manage the user data.
     *
     * @param interactor
     */
    @Inject
    public HomePresenter(FetchUsers interactor) {
        this.interactor = interactor;
    }

    /**
     * Makes the call for obtain a list of the users.
     */
    public void fetchUsers() {
        if (userList == null) {
            getUsersListObservable = interactor.execute();
            getUsersListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUsersListSubscriber);
        } else {
            view.createListHomeRecyclerView(userList);
        }
    }

    /**
     * Manages the callback of the execute call.
     */
    private Observer<List<User>> getUsersListSubscriber = new Observer<List<User>>() {
        @Override
        public void onError(Throwable e) {
            view.showMessageError();
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
            view.createListHomeRecyclerView(usersList);
        }
    };
}
