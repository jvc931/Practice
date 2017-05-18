package com.globant.practice.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.practice.domain.interactor.FetchUsersInteractor;
import com.globant.practice.domain.model.User;
import com.globant.practice.domain.service.GitHubApi;
import com.globant.practice.presentation.model.SubscriberListState;
import com.globant.practice.presentation.view.fragment.SubscriberListView;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jonathan.vargas on 16/05/2017.
 */
public class SubscriberListPresenterTest {
    @Mock
    private SubscriberListView mockView;
    @InjectMocks
    private SubscriberListPresenter presenter;
    @Mock(name = "subscriberListState")
    private SubscriberListState mockSubscriberListState;
    @Mock(name = "interactor")
    private FetchUsersInteractor interactor;
    @Mock(name = "userListObservable")
    private Observable<List<User>> mockUserListObservable;
    @Mock
    private GitHubApi mockApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mockView);
    }

    @BeforeClass
    public static void setUpRxSchedulers()throws Exception{
        final Scheduler scheduler1 = new Scheduler() {
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

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return scheduler1;
            }
        });

        RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler1;
            }
        });

        RxJavaPlugins.setNewThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return scheduler1;
            }
        });

        RxJavaPlugins.setSingleSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return scheduler1;
            }
        });

        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return scheduler1;
            }
        });
    }

    @Test
    public void fetchUsers() throws Exception {
        //when(presenter.isViewAttached()).thenReturn(true);
        //List<User> mockUserList = Arrays.asList(new User("mockUser","www.mockUrl.com"));
        //Observable<List<User>> mockObservableUserList = Observable.just(mockUserList);
        //when(mockSubscriberListState.isLoading()).thenReturn(true);
        //when(interactor.execute()).thenReturn(Observable.just(mockUserList));
        presenter.fetchUsers();
        verify(mockView).render(any(SubscriberListState.class));
    }


}