package com.example.gentlefolks.presentation.details;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.base.BaseFragment;
import com.example.gentlefolks.presentation.mainScreen.answerslist.Answer;
import com.example.gentlefolks.presentation.opinionsScreen.Opinion;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * Created by vl.melnikov on 24.02.2018.
 */

public class DetailsFragment extends BaseFragment<DetailsUiEvent, DetailsUiModel> {

	public static final String OPINION_KEY = "opinion-key";
	public static final String ANSWER_KEY = "answer-key";

	@BindView(R.id.title)
	RobotoTextView mTitle;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	@BindView(R.id.profile_image)
	CircularImageView mProfileImage;
	@BindView(R.id.authors_name)
	RobotoTextView mAuthorsName;
	@BindView(R.id.author_post)
	RobotoTextView mAuthorPost;
	@BindView(R.id.text_container)
	LinearLayout mTextContainer;
	@BindView(R.id.opinion)
	TextView mOpinion;
	Unbinder unbinder;

	public static DetailsFragment getInstance(Opinion opinion) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(OPINION_KEY, opinion);
		DetailsFragment fragment = new DetailsFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	public static DetailsFragment getInstance(Answer answer) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(ANSWER_KEY, answer);
		DetailsFragment fragment = new DetailsFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_details, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		mToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_close));
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});
		if (getArguments().getSerializable(ANSWER_KEY) != null) {
			renderAnswer((Answer) getArguments().getSerializable(ANSWER_KEY));
		} else if (getArguments().getSerializable(OPINION_KEY) != null) {
			renderOpinion((Opinion) getArguments().getSerializable(OPINION_KEY));
		}
	}

	private void renderOpinion(Opinion opinion) {
		Picasso.with(mProfileImage.getContext())
			.load(opinion.profilePhoto)
			.into(mProfileImage);
		mAuthorsName.setText(opinion.authorName);
		mAuthorPost.setText(opinion.authorPost);
		mOpinion.setText(opinion.opinion);
	}

	private void renderAnswer(Answer answer) {
		Picasso.with(mProfileImage.getContext())
			.load(answer.answerImage)
			.into(mProfileImage);
		mAuthorsName.setText(answer.authorName);
		mAuthorPost.setText(answer.authorPost);
		mOpinion.setText(answer.answer);
	}

	@Override
	protected Consumer<DetailsUiModel> getUIModelAction() {
		return new Consumer<DetailsUiModel>() {
			@Override
			public void accept(DetailsUiModel detailsUiModel) throws Exception {

			}
		};
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
}
