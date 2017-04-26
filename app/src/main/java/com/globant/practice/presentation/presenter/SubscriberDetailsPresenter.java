package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.FetchSubscriberProfile;
import com.globant.practice.domain.interactor.FetchSubscriberRepositories;
import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import com.globant.practice.presentation.model.SubscriberDetailsState;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Contains the methods that the SubscriberDetailsFragment needs.
 * Created by jonathan.vargas on 26/04/2017.
 */

public class SubscriberDetailsPresenter extends BasePresenter<SubscriberDetailsView> {
    private FetchSubscriberProfile profileInteractor;
    private FetchSubscriberRepositories repositoriesInteractor;
    private Observable<Profile> subscriberProfileObservable;
    private Observable<List<Repository>> subscriberRepositoryListObservable;
    private SubscriberDetailsState subscriberDetailsState;
    private Profile subscriberProfile;
    private List<Repository> subscriberRepositories;
    private String login;

    /**
     * Construct method of the SubscriberDetailsPresenter, receives a FetchSubscriberProfile and
     * FetchSubscriberRepositories instance for manage the subscriber data and initializes the
     * subscriberDetailsState for manage the states of the view.
     *
     * @param profileInteractor      FetchSubscriberProfile instance
     * @param repositoriesInteractor FetchSubscriberRepositories instance
     */
    @Inject
    public SubscriberDetailsPresenter(FetchSubscriberProfile profileInteractor, FetchSubscriberRepositories repositoriesInteractor) {
        this.profileInteractor = profileInteractor;
        this.repositoriesInteractor = repositoriesInteractor;
        subscriberDetailsState = new SubscriberDetailsState();
    }

    /**
     * Makes the call to obtain the subscriber details
     *
     * @param login login of the subscriber
     */
    public void fetchSubscriberDetails(String login) {
        if (subscriberDetailsState.isLoading() && subscriberProfile != null && subscriberRepositories != null) {
            subscriberDetailsState.setError(null);
            subscriberDetailsState.setProfile(subscriberProfile);
            subscriberDetailsState.setSubscriberRepositories(subscriberRepositories);
            subscriberDetailsState.setLoading(false);
            if (isViewAttached()) {
                view.render(subscriberDetailsState);
            }
        } else {
            subscriberDetailsState.setError(null);
            subscriberDetailsState.setProfile(null);
            subscriberDetailsState.setSubscriberRepositories(null);
            subscriberDetailsState.setLoading(true);
            if (isViewAttached()) {
                view.render(subscriberDetailsState);
            }
            this.login = login;
            subscriberProfileObservable = profileInteractor.execute(login);
            subscriberProfileObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getSubscriberProfile);
        }
    }

    /**
     * Manages the callback of the profileInteractor call and initializes the repositoriesInteractor
     * call
     */
    private Observer<Profile> getSubscriberProfile = new Observer<Profile>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Profile profile) {
            subscriberProfile = profile;
            subscriberRepositoryListObservable = repositoriesInteractor.execute(login);
            subscriberRepositoryListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getSubscriberRepositories);
        }

        @Override
        public void onError(Throwable e) {
            subscriberDetailsState.setLoading(false);
            subscriberDetailsState.setProfile(null);
            subscriberDetailsState.setSubscriberRepositories(null);
            if (isViewAttached()) {
                subscriberDetailsState.setError(view.getErrorMessageText());
                view.render(subscriberDetailsState);
            }
        }

        @Override
        public void onComplete() {
        }
    };

    /**
     * Manages the callback of the repositoriesInteractor call
     */
    private Observer<List<Repository>> getSubscriberRepositories = new Observer<List<Repository>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Repository> repositories) {
            subscriberRepositories = repositories;
            subscriberDetailsState.setError(null);
            subscriberDetailsState.setProfile(subscriberProfile);
            subscriberDetailsState.setSubscriberRepositories(subscriberRepositories);
            subscriberDetailsState.setLoading(false);
            if (isViewAttached()) {
                view.render(subscriberDetailsState);
            }
        }

        @Override
        public void onError(Throwable e) {
            subscriberDetailsState.setLoading(false);
            subscriberDetailsState.setProfile(null);
            subscriberDetailsState.setSubscriberRepositories(null);
            if (isViewAttached()) {
                subscriberDetailsState.setError(view.getErrorMessageText());
                view.render(subscriberDetailsState);
            }
        }

        @Override
        public void onComplete() {

        }
    };
}
