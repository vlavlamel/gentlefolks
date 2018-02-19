package com.example.gentlefolks.presentation.mainScreen.answerslist;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionMain;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

	private List<Answer> listAnswers;

	public void setData(List<Answer> listAnswers) {
		this.listAnswers = listAnswers;
		notifyDataSetChanged();
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
		@BindView(R.id.author)
		TextView author;


		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

		public void onBind(int position) {
			title.setText(listAnswers.get(position).answerTitle);
			author.setText(listAnswers.get(position).authorName);
		}
	}
}