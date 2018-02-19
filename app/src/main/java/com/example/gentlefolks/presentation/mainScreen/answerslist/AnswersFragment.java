package com.example.gentlefolks.presentation.mainScreen.answerslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * Created by vl.melnikov on 22.11.2017.
 */

public class AnswersFragment extends BaseFragment<AnswersFragmentUiEvent, AnswersFragmentUiModel> {


	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	Unbinder unbinder;

	private AnswersAdapter adapter;

	public static AnswersFragment getInstance() {
		return new AnswersFragment();
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
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new AnswersAdapter();
		mRecyclerView.setAdapter(adapter);
		sendEvent(new AnswersFragmentUiEvent());
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	@Override
	protected Consumer<AnswersFragmentUiModel> getUIModelAction() {
		return new Consumer<AnswersFragmentUiModel>() {
			@Override
			public void accept(AnswersFragmentUiModel answersFragmentUiModel) throws Exception {
				adapter.setData(answersFragmentUiModel.getOpinionMains());
			}
		};
	}
}
