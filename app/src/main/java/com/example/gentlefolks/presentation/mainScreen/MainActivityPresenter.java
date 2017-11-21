package com.example.gentlefolks.presentation.mainScreen;

import com.example.gentlefolks.presentation.base.ViewPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class MainActivityPresenter extends ViewPresenter<MainActivityUiEvent, MainActivityUiModel> {

	@Inject
	public MainActivityPresenter() {
	}

	@Override
	protected ObservableTransformer<MainActivityUiEvent, MainActivityUiModel> getViewPresenterTransformer() {
		return new ObservableTransformer<MainActivityUiEvent, MainActivityUiModel>() {
			@Override
			public ObservableSource<MainActivityUiModel> apply(Observable<MainActivityUiEvent> upstream) {
				return upstream.flatMap(new Function<MainActivityUiEvent, ObservableSource<MainActivityUiModel>>() {
					@Override
					public ObservableSource<MainActivityUiModel> apply(MainActivityUiEvent mainActivityUiEvent) throws Exception {
						return Observable.just(new MainActivityUiModel());
					}
				});
			}
		};
	}
}
