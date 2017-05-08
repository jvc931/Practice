package com.globant.practice.presentation.presenter;

import com.globant.practice.domain.interactor.FetchSubscriberProfileInteractor;
import com.globant.practice.domain.interactor.FetchSubscriberRepositoriesInteractor;
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
 * Makes the api call to get the subscriber information if this call has results them makes the api
 * call to get the subscriber repositories, if one of the both api calls has an error informs to the
 * view that have to render a message error.
 * Created by jonathan.vargas on 26/04/2017.
 */

public class SubscriberDetailsPresenter extends BasePresenter<SubscriberDetailsView> {
    private FetchSubscriberProfileInteractor profileInteractor;
    private FetchSubscriberRepositoriesInteractor repositoriesInteractor;
    private Observable<Profile> subscriberProfileObservable;
    private Observable<List<Repository>> subscriberRepositoryListObservable;
    private SubscriberDetailsState subscriberDetailsState;

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
            subscriberDetailsState.setProfile(profile);
            fetchSubscriberRepositories();
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
            subscriberDetailsState.setSubscriberRepositories(repositories);
            subscriberDetailsState.setError(null);
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

    /**
     * Construct method of the SubscriberDetailsPresenter, receives a FetchSubscriberProfileInteractor and
     * FetchSubscriberRepositoriesInteractor instance for manage the subscriber data and initializes the
     * subscriberDetailsState for manage the states of the view.
     *
     * @param profileInteractor      FetchSubscriberProfileInteractor instance
     * @param repositoriesInteractor FetchSubscriberRepositoriesInteractor instance
     */
    @Inject
    public SubscriberDetailsPresenter(FetchSubscriberProfileInteractor profileInteractor, FetchSubscriberRepositoriesInteractor repositoriesInteractor) {
        this.profileInteractor = profileInteractor;
        this.repositoriesInteractor = repositoriesInteractor;
        subscriberDetailsState = new SubscriberDetailsState();
    }

    /**
     * Makes the call to obtain the subscriber details and receive the subscriber login
     *
     * @param login subscriber login
     */
    public void fetchSubscriberDetails(String login) {
        subscriberDetailsState.setLogin(login);
        if (subscriberDetailsState.getProfile() != null && subscriberDetailsState.getSubscriberRepositories() != null) {
            subscriberDetailsState.setError(null);
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
                subscriberProfileObservable = profileInteractor.execute(subscriberDetailsState.getLogin());
                subscriberProfileObservable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getSubscriberProfile);
            }
        }
    }

    /**
     * Makes the api call to get the subscriber repositories
     */
    private void fetchSubscriberRepositories() {
        if (isViewAttached()) {
            subscriberRepositoryListObservable = repositoriesInteractor.execute(subscriberDetailsState.getLogin());
            subscriberRepositoryListObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getSubscriberRepositories);
        }
    }
}
