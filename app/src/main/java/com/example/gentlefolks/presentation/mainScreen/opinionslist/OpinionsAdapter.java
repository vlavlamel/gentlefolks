package com.example.gentlefolks.presentation.mainScreen.opinionslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpinionsAdapter extends RecyclerView.Adapter<OpinionsAdapter.ViewHolder> {

	private List<OpinionMain> listOpinions;
	private OpinionsClickListener mOnClickListener;

	public OpinionsAdapter(OpinionsClickListener clickListener) {
		mOnClickListener = clickListener;
	}

	public void setData(List<OpinionMain> listOpinions) {
		this.listOpinions = listOpinions;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View inflatedView = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_main_opinion, parent, false);
		return new ViewHolder(inflatedView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.onBind(position);
	}

	@Override
	public int getItemCount() {
		if (listOpinions == null) return 0;
		else return listOpinions.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		@BindView(R.id.item_main_image)
		ImageView imageview;
		@BindView(R.id.item_main_name)
		RobotoTextView nameview;


		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
			view.setOnClickListener(this);
		}

		public void onBind(int position) {
			Picasso.with(imageview.getContext())
				.load(listOpinions.get(position).opinionBackground)
				.into(imageview, new Callback() {
					@Override
					public void onSuccess() {

					}

					@Override
					public void onError() {

					}
				});
			nameview.setText(listOpinions.get(position).hashtag);
		}

		@Override
		public void onClick(View view) {
			mOnClickListener.onClick(listOpinions.get(getAdapterPosition()).opinionId, listOpinions.get(getAdapterPosition()).hashtag, listOpinions.get(getAdapterPosition()).opinionBackground);
		}
	}
}