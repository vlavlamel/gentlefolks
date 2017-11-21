package com.example.gentlefolks.di;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.example.gentlefolks.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vl.melnikov on 08.10.17.
 */
@Module
public abstract class BaseActivityModule {

	@Binds
	@ActivityScope
	abstract Activity activity(AppCompatActivity appCompatActivity);

}
