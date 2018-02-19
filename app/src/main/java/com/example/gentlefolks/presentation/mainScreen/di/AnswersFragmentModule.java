package com.example.gentlefolks.presentation.mainScreen.di;

import com.example.gentlefolks.di.scope.FragmentScope;
import com.example.gentlefolks.presentation.base.EventModelPresenter;
import com.example.gentlefolks.presentation.mainScreen.answerslist.AnswersFragmentUiEvent;
import com.example.gentlefolks.presentation.mainScreen.answerslist.AnswersFragmentUiModel;
import com.example.gentlefolks.presentation.mainScreen.answerslist.AnswersPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vl.melnikov on 20.02.2018.
 */

@Module
public abstract class AnswersFragmentModule {

	@Binds
	@FragmentScope
	public abstract EventModelPresenter<AnswersFragmentUiEvent, AnswersFragmentUiModel> providePresenter(AnswersPresenter presenter);

}

