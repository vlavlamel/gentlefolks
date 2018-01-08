package com.example.gentlefolks.presentation.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.base.BaseActivityWithFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivityWithFragment<MainActivityUiEvent, MainActivityUiModel> {

	@BindView(R.id.tab_layout)
	TabLayout mTabLayout;
	@BindView(R.id.pager)
	ViewPager mPager;

	public static Intent getStartIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		mPager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
		mTabLayout.setupWithViewPager(mPager);
	}

	public void showMessage(){
		Snackbar.make(mPager,"blya",Snackbar.LENGTH_SHORT).show();
	}

	@Override
	protected Consumer<MainActivityUiModel> getUIModelAction() {
		return new Consumer<MainActivityUiModel>() {
			@Override
			public void accept(MainActivityUiModel mainActivityUiModel) throws Exception {
				showMessage();
			}
		};
	}
}