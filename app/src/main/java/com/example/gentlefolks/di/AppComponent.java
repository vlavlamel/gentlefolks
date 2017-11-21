package com.example.gentlefolks.di;

import com.example.gentlefolks.GentlefolksApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = AppModule.class)
interface AppComponent extends AndroidInjector<GentlefolksApp> {

	@Component.Builder
	abstract class Builder extends AndroidInjector.Builder<GentlefolksApp> {
	}
}

