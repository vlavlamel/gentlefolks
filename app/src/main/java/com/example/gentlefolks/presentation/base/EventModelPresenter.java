package com.example.gentlefolks.presentation.base;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface EventModelPresenter<E, M> {
	void setUIEventObservable(Observable<E> observable);
	void addSubscription(Disposable subscription);
	Observable<M> getUIModelObservable();
	void clearSubscriptions();
}
