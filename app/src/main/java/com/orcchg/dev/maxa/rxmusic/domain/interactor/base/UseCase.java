package com.orcchg.dev.maxa.rxmusic.domain.interactor.base;

import com.orcchg.dev.maxa.rxmusic.domain.executor.PostExecuteScheduler;
import com.orcchg.dev.maxa.rxmusic.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * @param <Result> Generic type of result on finish of execution.
 */
public abstract class UseCase<Result> {

    private final ThreadExecutor threadExecutor;
    private final PostExecuteScheduler postExecuteScheduler;

    private Subscription subscription = Subscriptions.empty();

    /**
     * Basic construction of a {@link UseCase} class instance.
     *
     * @param threadExecutor where to push the request
     * @param postExecuteScheduler where to observe the result
     */
    protected UseCase(ThreadExecutor threadExecutor, PostExecuteScheduler postExecuteScheduler) {
        this.threadExecutor = threadExecutor;
        this.postExecuteScheduler = postExecuteScheduler;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable<Result> doAction();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #doAction()}.
     */
    public void execute(Subscriber<Result> subscriber) {
        this.subscription = this.doAction()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecuteScheduler.getScheduler())
                .subscribe(subscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}
