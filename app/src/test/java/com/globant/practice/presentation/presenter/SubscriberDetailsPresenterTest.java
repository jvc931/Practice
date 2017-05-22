package com.globant.practice.presentation.presenter;

import android.support.annotation.NonNull;
import com.globant.practice.domain.interactor.FetchSubscriberProfileInteractor;
import com.globant.practice.domain.interactor.FetchSubscriberRepositoriesInteractor;
import com.globant.practice.domain.model.Profile;
import com.globant.practice.domain.model.Repository;
import com.globant.practice.presentation.model.SubscriberDetailsState;
import com.globant.practice.presentation.view.fragment.SubscriberDetailsView;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for the SubscriberDetailsPresenter methods
 * Created by jonathan.vargas on 19/05/2017.
 */
public class SubscriberDetailsPresenterTest {
    @InjectMocks
    private SubscriberDetailsPresenter presenter;
    @Mock
    private SubscriberDetailsView mockView;
    @Mock(name = "profileInteractor")
    private FetchSubscriberProfileInteractor profileInteractor;
    @Mock(name = "repositoriesInteractor")
    private FetchSubscriberRepositoriesInteractor repositoriesInteractor;

    /**
     * Initializes the mock objects and attach the view to the presenter
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mockView);
    }

    /**
     * Initializes the necessary Schedulers needed for mock the responses of the web service call
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpRxSchedulers() throws Exception {
        final Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(@NonNull Runnable command) {
                        command.run();
                    }
                });
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });

        RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });

        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });

        RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });
    }

    /**
     * Unit test of fetchSubscriberDetails makes the call to profileInteractor execute method,
     * will have response a exception, first should show the loading indicator with the profileInteractor
     * response should dismiss the loading indicator and show the error message
     *
     * @throws Exception
     */
    @Test
    public void fetchSubscriberDetails_withViewAttachedAndProfileCallFails_ShouldRenderLoadingIndicatorAndError() throws Exception {
        when(profileInteractor.execute(anyString())).thenReturn(Observable.<Profile>error(new UnknownHostException()));
        presenter.fetchSubscriberDetails(anyString());
        verify(mockView, times(2)).render(any(SubscriberDetailsState.class));
        verify(repositoriesInteractor, never()).execute(anyString());
    }

    /**
     * Unit test of fetchSubscriberDetails makes the call to profileInteractor execute method,
     * will have response the profile information with has this response calls the repositoryInteractor
     * execute method that will have response a exception, first should show the loading
     * indicator with the repositoryInteractor response dismiss the loading indicator and show the error message
     *
     * @throws Exception
     */
    @Test
    public void fetchSubscriberDetails_withViewAttachedAndRepositoriesCallFails_ShouldRenderLoadingIndicatorAndError() throws Exception {
        Profile mockProfile = new Profile("mockLogin", "www.mockulr.com", "www.mockurl.com", "mockName", "mockCompany", "mockLocation", 1, 1, 1);
        when(profileInteractor.execute(anyString())).thenReturn(Observable.just(mockProfile));
        when(repositoriesInteractor.execute(anyString())).thenReturn(Observable.<List<Repository>>error(new UnknownHostException()));
        presenter.fetchSubscriberDetails(anyString());
        verify(mockView, times(2)).render(any(SubscriberDetailsState.class));
        verify(repositoriesInteractor).execute(anyString());
    }

    /**
     * Unit test of fetchSubscriberDetails makes the call to profileInteractor execute method, will
     * have response the profile information with has this response calls the repositoryInteractor
     * execute method that will have response a list of the user repositories, first should show the loading
     * indicator with the repositoryInteractor response dismiss the loading indicator and show the
     * profile and repository information
     *
     * @throws Exception
     */
    @Test
    public void fetchSubscriberDetails_withViewAttachedAndSuccessfulProfileAndRepositoryCalls_ShouldRenderProfileAndRepositories() throws Exception {
        Profile mockProfile = new Profile("mockLogin", "www.mockulr.com", "www.mockurl.com", "mockName", "mockCompany", "mockLocation", 1, 1, 1);
        List<Repository> mockUserRepositoryList = Arrays.asList(new Repository("mockName", "www.mockUrl.com"));
        when(profileInteractor.execute(anyString())).thenReturn(Observable.just(mockProfile));
        when(repositoriesInteractor.execute(anyString())).thenReturn(Observable.just(mockUserRepositoryList));
        presenter.fetchSubscriberDetails(anyString());
        verify(mockView, times(2)).render(any(SubscriberDetailsState.class));
        verify(repositoriesInteractor).execute(anyString());
    }
}