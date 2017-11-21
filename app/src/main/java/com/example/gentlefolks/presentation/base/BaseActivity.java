package com.example.gentlefolks.presentation.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity<E, M> extends AppCompatActivity
	implements HasSupportFragmentInjector {

	@Inject
	EventModelPresenter<E, M> presenter;

	@Inject
	DispatchingAndroidInjector<Fragment> supportFragmentInjector;

	private ObservableEmitter<E> eventEmitter;

	private CompositeDisposable mDisposable = new CompositeDisposable();

	private final static String TAG = BaseActivity.class.getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		Log.v("TEST_RX", " BaseActivity OnCreate");
		subscribeToPresenter();
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
			.observeOn(AndroidSchedulers.mainThread())
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
	protected void onResume() {
		super.onResume();
		if (mDisposable.size() == 0){
			subscribeToPresenter();
			presenter.setUIEventObservable(getEventObservable());
		}
		Log.d("","");
	}

	@Override
	protected void onPause() {
		super.onPause();
		clearDisposables();
	}

	public EventModelPresenter<E, M> getPresenter() {
		return presenter;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isFinishing()) presenter.clearSubscriptions();
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return supportFragmentInjector;
	}

	protected void addDisposable(Disposable sub) {
		mDisposable.add(sub);
	}

	protected void clearDisposables() {
		mDisposable.clear();
	}
}