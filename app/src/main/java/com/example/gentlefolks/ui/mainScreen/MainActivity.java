package com.example.gentlefolks.ui.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gentlefolks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vl.melnikov on 06.11.17.
 */

public class MainActivity extends AppCompatActivity {

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
}
