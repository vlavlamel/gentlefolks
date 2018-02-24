package com.example.gentlefolks.presentation.details.di;

import com.example.gentlefolks.di.scope.FragmentScope;
import com.example.gentlefolks.presentation.base.EventModelPresenter;
import com.example.gentlefolks.presentation.details.DetailsPresenter;
import com.example.gentlefolks.presentation.details.DetailsUiEvent;
import com.example.gentlefolks.presentation.details.DetailsUiModel;
import dagger.Binds;
import dagger.Module;

/**
 * Created by vl.melnikov on 25.11.2017.
 */

@Module
public abstract class DetailsModule {

	@Binds
	@FragmentScope
	public abstract EventModelPresenter<DetailsUiEvent, DetailsUiModel> providePresenter(DetailsPresenter presenter);

}
