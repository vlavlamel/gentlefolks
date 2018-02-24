package com.example.gentlefolks.presentation.mainScreen.di;

import android.support.v7.app.AppCompatActivity;

import com.example.gentlefolks.di.scope.ActivityScope;
import com.example.gentlefolks.di.scope.FragmentScope;
import com.example.gentlefolks.presentation.base.EventModelPresenter;
import com.example.gentlefolks.presentation.details.DetailsFragment;
import com.example.gentlefolks.presentation.details.di.DetailsModule;
import com.example.gentlefolks.presentation.mainScreen.MainActivity;
import com.example.gentlefolks.presentation.mainScreen.MainActivityPresenter;
import com.example.gentlefolks.presentation.mainScreen.MainActivityUiEvent;
import com.example.gentlefolks.presentation.mainScreen.MainActivityUiModel;
import com.example.gentlefolks.presentation.mainScreen.answerslist.AnswersFragment;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsGridFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

	@Binds
	@ActivityScope
	abstract AppCompatActivity appCompatActivity(MainActivity mainActivity);

	@Binds
	@ActivityScope
	public abstract EventModelPresenter<MainActivityUiEvent, MainActivityUiModel> providePresenter(MainActivityPresenter presenter);

	@FragmentScope
	@ContributesAndroidInjector(modules = {OpinionsGridFragmentModule.class})
	abstract OpinionsGridFragment contributeMainFragmentInjector();

	@FragmentScope
	@ContributesAndroidInjector(modules = {AnswersFragmentModule.class})
	abstract AnswersFragment contributeAnswersFragmentInjector();

	@FragmentScope
	@ContributesAndroidInjector(modules = {DetailsModule.class})
	abstract DetailsFragment contributeDetailsFragmentInjector();

}
