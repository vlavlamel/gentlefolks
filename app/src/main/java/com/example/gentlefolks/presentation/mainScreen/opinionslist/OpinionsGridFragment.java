package com.example.gentlefolks.presentation.mainScreen.opinionslist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gentlefolks.R;
import com.example.gentlefolks.api.ApiFactory;
import com.example.gentlefolks.presentation.base.BaseFragment;
import com.example.gentlefolks.presentation.opinionsScreen.OpinionsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OpinionsGridFragment extends BaseFragment<OpinionsFragmentUiEvent, OpinionsFragmentUiModel> implements View.OnClickListener {

	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	Unbinder unbinder;

	private OpinionsAdapter adapter;

	public static OpinionsGridFragment getInstance() {
		return new OpinionsGridFragment();
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
		mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		adapter = new OpinionsAdapter(this);
		mRecyclerView.setAdapter(adapter);
		sendEvent(new OpinionsFragmentUiEvent());
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

	@Override
	protected Consumer<OpinionsFragmentUiModel> getUIModelAction() {
		return new Consumer<OpinionsFragmentUiModel>() {
			@Override
			public void accept(OpinionsFragmentUiModel opinionsFragmentUiModel) throws Exception {
				adapter.setData(opinionsFragmentUiModel.getOpinionMains());
			}
		};
	}
}
