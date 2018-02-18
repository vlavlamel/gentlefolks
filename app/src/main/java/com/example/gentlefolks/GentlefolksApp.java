package com.example.gentlefolks;

import android.app.Activity;
import android.app.Application;


import com.crashlytics.android.Crashlytics;
import com.example.gentlefolks.di.DaggerAppComponent;

import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class GentlefolksApp extends Application implements HasActivityInjector {

	@Inject
	DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

	@Override
	public void onCreate() {
		super.onCreate();
		Fabric.with(this, new Crashlytics());
		DaggerAppComponent.builder()
			.create(this)
			.inject(this);
	}

	@Override
	public DispatchingAndroidInjector<Activity> activityInjector() {
		return dispatchingActivityInjector;
	}

}
