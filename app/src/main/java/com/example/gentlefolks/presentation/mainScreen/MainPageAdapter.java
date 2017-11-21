package com.example.gentlefolks.presentation.mainScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gentlefolks.presentation.mainScreen.answerslist.AnswersFragment;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsGridFragment;


public class MainPageAdapter extends FragmentPagerAdapter {

	public MainPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position){
			case 0:
				return OpinionsGridFragment.getInstance();
			case 1:
				return new AnswersFragment();
			default:
				return null;
		}
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
