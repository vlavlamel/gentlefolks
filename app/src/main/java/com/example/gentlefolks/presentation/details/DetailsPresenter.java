package com.example.gentlefolks.presentation.details;

import com.example.gentlefolks.api.ApiFactory;
import com.example.gentlefolks.presentation.base.ViewPresenter;
import com.example.gentlefolks.presentation.opinionsScreen.Opinion;

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

public class DetailsPresenter extends ViewPresenter<DetailsUiEvent, DetailsUiModel> {

	@Inject
	ApiFactory mApiFactory;

	@Inject
	public DetailsPresenter() {
	}

	@Override
	protected ObservableTransformer<DetailsUiEvent, DetailsUiModel> getViewPresenterTransformer() {
		return new ObservableTransformer<DetailsUiEvent, DetailsUiModel>() {
			@Override
			public ObservableSource<DetailsUiModel> apply(Observable<DetailsUiEvent> upstream) {
				return upstream.flatMap(new Function<DetailsUiEvent, ObservableSource<DetailsUiModel>>() {
					@Override
					public ObservableSource<DetailsUiModel> apply(DetailsUiEvent opinionsFragmentUiEvent) throws Exception {
						return Observable.empty();
					}
				});
			}
		};
	}
}
