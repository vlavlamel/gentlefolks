package com.example.gentlefolks.presentation.mainScreen.opinionslist;

import com.example.gentlefolks.api.ApiFactory;
import com.example.gentlefolks.presentation.base.ViewPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OpinionsGridPresenter extends ViewPresenter<OpinionsFragmentUiEvent, OpinionsFragmentUiModel> {

	@Inject
	ApiFactory mApiFactory;

	@Inject
	public OpinionsGridPresenter() {
	}

	@Override
	protected ObservableTransformer<OpinionsFragmentUiEvent, OpinionsFragmentUiModel> getViewPresenterTransformer() {
		return new ObservableTransformer<OpinionsFragmentUiEvent, OpinionsFragmentUiModel>() {
			@Override
			public ObservableSource<OpinionsFragmentUiModel> apply(Observable<OpinionsFragmentUiEvent> upstream) {
				return upstream.flatMap(new Function<OpinionsFragmentUiEvent, ObservableSource<OpinionsFragmentUiModel>>() {
					@Override
					public ObservableSource<OpinionsFragmentUiModel> apply(OpinionsFragmentUiEvent opinionsFragmentUiEvent) throws Exception {
						return mApiFactory.getApi()
							.opinionsList().map(new Function<List<OpinionMain>, OpinionsFragmentUiModel>() {
								@Override
								public OpinionsFragmentUiModel apply(List<OpinionMain> opinionMains) throws Exception {
									return new OpinionsFragmentUiModel(opinionMains);
								}
							})
							.subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread());
					}
				});
			}
		};
	}
}
