package com.example.gentlefolks.presentation.base;

import android.util.Log;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public abstract class ViewPresenter<E, M> implements EventModelPresenter<E, M> {

	private CompositeDisposable compositeSubscription = new CompositeDisposable();

	protected ConnectableObservable<M> uiModelObservable;

	protected ObservableEmitter<Observable<E>> eventObservableEmitter;

	public ViewPresenter() {
		uiModelObservable =
			Observable.create(new ObservableOnSubscribe<Observable<E>>() {
				@Override
				public void subscribe(ObservableEmitter<Observable<E>> e) throws Exception {
					eventObservableEmitter = e;
				}
			})
				.doOnEach(new Consumer<Notification<Observable<E>>>() {
					@Override
					public void accept(Notification<Observable<E>> observableNotification) throws Exception {
						Log.v("TEST_RX", observableNotification.toString() + " in constructor ViewPresenter");
					}
				})
				.flatMap(new Function<Observable<E>, ObservableSource<M>>() {
					@Override
					public ObservableSource<M> apply(Observable<E> eObservable) throws Exception {
						return eObservable.compose(getViewPresenterTransformer());
					}
				})
				.replay(1);
		uiModelObservable.connect();
	}

	@Override
	public void setUIEventObservable(Observable<E> observable) {
		if (eventObservableEmitter != null)
			eventObservableEmitter.onNext(observable);
	}

	protected abstract ObservableTransformer<E, M> getViewPresenterTransformer();

	@Override
	public Observable<M> getUIModelObservable() {
		return uiModelObservable;
	}

	@Override
	public void addSubscription(Disposable subscription) {
		compositeSubscription.add(subscription);
	}

	@Override
	public void clearSubscriptions() {
		compositeSubscription.clear();
	}
}
