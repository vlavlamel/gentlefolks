package com.example.gentlefolks.presentation.mainScreen.answerslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.details.DetailsFragment;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionMain;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

	private List<Answer> listAnswers;
	private Context ctx;
	private AnswerClickListener mAnswerClickListener;

	public void setData(List<Answer> listAnswers) {
		this.listAnswers = listAnswers;
		notifyDataSetChanged();
	}

	public AnswersAdapter(AnswersFragment fragment) {
		this.ctx = fragment.getContext();
		mAnswerClickListener = fragment;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View inflatedView = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_answer, parent, false);
		return new ViewHolder(inflatedView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.onBind(position);
	}

	@Override
	public int getItemCount() {
		if (listAnswers == null) return 0;
		else return listAnswers.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.answer_title)
		TextView title;
		@BindView(R.id.answering)
		TextView answering;
		@BindView(R.id.image)
		ImageView imageview;
		@BindView(R.id.profile_image)
		ImageView profileImage;
		@BindView(R.id.item_hashtag)
		RobotoTextView hashtag;


		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

		public void onBind(int position) {
			final Answer answer = listAnswers.get(position);
			title.setText(answer.answerTitle);
			SpannableStringBuilder authorTitle = new SpannableStringBuilder(ctx.getString(R.string.author_answer, answer.authorName, answer.authorPost));
			authorTitle.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 9, 9 + answer.authorName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			answering.setText(authorTitle);
			Picasso.with(imageview.getContext())
				.load(answer.answerBkg)
				.into(imageview);
			Picasso.with(profileImage.getContext())
				.load(answer.answerImage)
				.into(profileImage);
			if (!TextUtils.isEmpty(answer.hashtag))
				hashtag.setText(answer.hashtag);
			if (!TextUtils.isEmpty(answer.answer))
				title.getRootView()
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							mAnswerClickListener.onClick(answer);
						}
					});
		}
	}
}