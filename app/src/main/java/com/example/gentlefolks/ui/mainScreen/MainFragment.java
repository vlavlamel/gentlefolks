package com.example.gentlefolks.ui.mainScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gentlefolks.R;
import com.example.gentlefolks.api.ApiGetData;
import com.example.gentlefolks.ui.opinionsScreen.OpinionsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vl.melnikov on 09.11.17.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	Unbinder unbinder;

	public static MainFragment getInstance() {
		return new MainFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_opinions, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.v("DishesFragment", "TEST onViewCreated" + this.hashCode());
		mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		final MainAdapter adapter = new MainAdapter(this);
		mRecyclerView.setAdapter(adapter);
		ApiGetData apiGetData = new ApiGetData();
		apiGetData.getApi()
			.opinionsList()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeOn(Schedulers.io())
			.subscribe(new Consumer<List<OpinionMain>>() {
				@Override
				public void accept(List<OpinionMain> opinionMains) throws Exception {
					adapter.setData(opinionMains);
				}
			});
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(getActivity(), OpinionsActivity.class);
		startActivity(intent);
	}
}
