package com.example.gentlefolks.ui.mainScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MainPageAdapter extends FragmentPagerAdapter {

	public MainPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return MainFragment.getInstance();
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Мнения";
			case 1:
				return "Ответы";
			default:
				return "";
		}
	}

}
