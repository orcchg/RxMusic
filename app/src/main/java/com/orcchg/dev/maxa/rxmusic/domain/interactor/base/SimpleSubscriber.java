package com.orcchg.dev.maxa.rxmusic.domain.interactor.base;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class SimpleSubscriber<Result> extends rx.Subscriber<Result> {

    @Override
    public void onCompleted() {
        // no-op
    }

    @Override
    public void onError(Throwable e) {
        // no-op
    }

    @Override
    public void onNext(Result result) {
        // no-op
    }
}
