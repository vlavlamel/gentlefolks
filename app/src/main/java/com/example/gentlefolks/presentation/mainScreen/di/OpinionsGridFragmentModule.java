package com.example.gentlefolks.presentation.mainScreen.di;

import com.example.gentlefolks.di.scope.FragmentScope;
import com.example.gentlefolks.presentation.base.EventModelPresenter;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsFragmentUiEvent;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsFragmentUiModel;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsGridPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class OpinionsGridFragmentModule {

	@Binds
	@FragmentScope
	public abstract EventModelPresenter<OpinionsFragmentUiEvent, OpinionsFragmentUiModel> providePresenter(OpinionsGridPresenter presenter);

}
