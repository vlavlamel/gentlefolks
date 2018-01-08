package com.example.gentlefolks.presentation.base;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by vl.melnikov on 25.11.2017.
 */

public abstract class BaseActivityWithFragment<E, M> extends BaseActivity<E, M> implements HasSupportFragmentInjector {

	@Inject
	DispatchingAndroidInjector<Fragment> supportFragmentInjector;

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return supportFragmentInjector;
	}

}
