package com.globant.practice.presentation.presenter;

import android.support.annotation.NonNull;
import com.globant.practice.domain.interactor.FetchUsersInteractor;
import com.globant.practice.domain.model.User;
import com.globant.practice.presentation.model.SubscriberListState;
import com.globant.practice.presentation.view.fragment.SubscriberListView;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test of the SubscriberListPresenter methods
 * Created by jonathan.vargas on 16/05/2017.
 */
public class SubscriberListPresenterTest {
    @Mock
    private SubscriberListView mockView;
    @InjectMocks
    private SubscriberListPresenter presenter;
    @Mock(name = "interactor")
    private FetchUsersInteractor interactor;

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
     * Unit test for the fetchUser method with a valid response of the web services, first should show
     * the loading indicator when receive the mock list response should dismiss the loading indicator
     * and show the subscribers
     *
     * @throws Exception
     */
    @Test
    public void fetchUsers_withViewAttachedAndWebserviceResponse_ShouldShowLoadingIndicatorAndSubscribers() throws Exception {
        List<User> mockUserList = Arrays.asList(new User("mockUser", "www.mockUrl.com"));
        when(interactor.execute()).thenReturn(Observable.just(mockUserList));
        presenter.fetchUsers();
        verify(mockView, times(2)).render(any(SubscriberListState.class));
    }

    /**
     * Unit test for the fetchUser method with a error response of the web services, first should show
     * the loading indicator when receive the error response should dismiss the loading indicator
     * and show the error message
     *
     * @throws Exception
     */
    @Test
    public void fetchUsers_withViewAttachedAndWebserviceErrorResponse_ShouldShowLoadingIndicatorAndErrorMessage() throws Exception {
        when(interactor.execute()).thenReturn(Observable.<List<User>>error(new UnknownHostException()));
        presenter.fetchUsers();
        verify(mockView, times(2)).render(any(SubscriberListState.class));
    }
}