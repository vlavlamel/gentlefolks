package com.example.gentlefolks.presentation.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFragment<E, M> extends Fragment {

	private final static String TAG = BaseFragment.class.getSimpleName();

	private CompositeDisposable mDisposable = new CompositeDisposable();

	@Inject
	EventModelPresenter<E, M> presenter;

	private ObservableEmitter<E> eventEmitter;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		AndroidSupportInjection.inject(this);
		presenter.setUIEventObservable(getEventObservable());
	}

	protected Observable<E> getEventObservable() {
		return Observable.create(
			new ObservableOnSubscribe<E>() {
				@Override
				public void subscribe(ObservableEmitter<E> eEmitter) throws Exception {
					eventEmitter = eEmitter;
				}
			});
	}

	protected void sendEvent(E event) {
		if (eventEmitter != null) {
			eventEmitter.onNext(event);
		}
	}

	protected void subscribeToPresenter() {
		addDisposable(presenter.getUIModelObservable()
			.subscribe(getUIModelAction(), getThrowableAction()));
	}

	protected abstract Consumer<M> getUIModelAction();

	protected Consumer<Throwable> getThrowableAction() {
		return new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				Log.v("TAG", throwable.getMessage());
			}
		};
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (getActivity().isFinishing()) presenter.clearSubscriptions();
	}

	@Override
	public void onResume() {
		super.onResume();
		subscribeToPresenter();
	}

	@Override
	public void onPause() {
		super.onPause();
		clearDisposables();
	}

	public EventModelPresenter<E, M> getPresenter() {
		return presenter;
	}

	protected void addDisposable(Disposable sub) {
		mDisposable.add(sub);
	}

	protected void clearDisposables() {
		mDisposable.clear();
	}

}
