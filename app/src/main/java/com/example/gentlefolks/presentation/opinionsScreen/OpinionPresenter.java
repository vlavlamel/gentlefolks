package com.example.gentlefolks.presentation.opinionsScreen;

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

/**
 * Created by vl.melnikov on 25.11.2017.
 */

public class OpinionPresenter extends ViewPresenter<OpinionUiEvent, OpinionUiModel> {

	@Inject
	ApiFactory mApiFactory;

	@Inject
	public OpinionPresenter() {
	}

	@Override
	protected ObservableTransformer<OpinionUiEvent, OpinionUiModel> getViewPresenterTransformer() {
		return new ObservableTransformer<OpinionUiEvent, OpinionUiModel>() {
			@Override
			public ObservableSource<OpinionUiModel> apply(Observable<OpinionUiEvent> upstream) {
				return upstream.flatMap(new Function<OpinionUiEvent, ObservableSource<OpinionUiModel>>() {
					@Override
					public ObservableSource<OpinionUiModel> apply(OpinionUiEvent opinionsFragmentUiEvent) throws Exception {
						return mApiFactory.getApi()
							.opinionList(opinionsFragmentUiEvent.getOpinion_id())
							.map(new Function<List<Opinion>, OpinionUiModel>() {
								@Override
								public OpinionUiModel apply(List<Opinion> opinionMains) throws Exception {
									return new OpinionUiModel(opinionMains);
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
