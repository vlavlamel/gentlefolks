package com.example.gentlefolks.presentation.mainScreen.answerslist;

import com.example.gentlefolks.api.ApiFactory;
import com.example.gentlefolks.presentation.base.ViewPresenter;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionMain;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsFragmentUiEvent;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsFragmentUiModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vl.melnikov on 18.02.2018.
 */

public class AnswersPresenter extends ViewPresenter<AnswersFragmentUiEvent, AnswersFragmentUiModel> {

	@Inject
	ApiFactory mApiFactory;

	@Inject
	public AnswersPresenter() {
	}

	@Override
	protected ObservableTransformer<AnswersFragmentUiEvent, AnswersFragmentUiModel> getViewPresenterTransformer() {
		return new ObservableTransformer<AnswersFragmentUiEvent, AnswersFragmentUiModel>() {
			@Override
			public ObservableSource<AnswersFragmentUiModel> apply(Observable<AnswersFragmentUiEvent> upstream) {
				return upstream.flatMap(new Function<AnswersFragmentUiEvent, ObservableSource<AnswersFragmentUiModel>>() {
					@Override
					public ObservableSource<AnswersFragmentUiModel> apply(AnswersFragmentUiEvent opinionsFragmentUiEvent) throws Exception {
						return mApiFactory.getApi()
							.answerList()
							.map(new Function<List<Answer>, AnswersFragmentUiModel>() {
								@Override
								public AnswersFragmentUiModel apply(List<Answer> opinionMains) throws Exception {
									return new AnswersFragmentUiModel(opinionMains);
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
