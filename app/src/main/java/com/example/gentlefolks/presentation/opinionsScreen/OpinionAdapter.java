package com.example.gentlefolks.presentation.opinionsScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vl.melnikov on 19.11.17.
 */

public class OpinionAdapter extends RecyclerView.Adapter<OpinionAdapter.ViewHolder> {

	private List<Opinion> listOpinions;

	public void setData(List<Opinion> listOpinions) {
		this.listOpinions = listOpinions;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View inflatedView = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_opinion, parent, false);
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

	class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.profile_image)
		CircularImageView mProfileImage;
		@BindView(R.id.quantity_likes)
		RobotoTextView mQuantityLikes;
		@BindView(R.id.authors_name)
		RobotoTextView mAuthorsName;
		@BindView(R.id.author_post)
		RobotoTextView mAuthorPost;
		@BindView(R.id.quote)
		RobotoTextView mQuote;

		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

		public void onBind(int position) {
			Opinion opinion = listOpinions.get(position);
			mAuthorsName.setText(opinion.getAuthor());
			mQuote.setText(opinion.getOpinion());
			mProfileImage.setImageResource(opinion.getImage_profile());
			mQuantityLikes.setText(opinion.getLikes());
			mAuthorPost.setText(opinion.getPost());
		}

		@OnClick(R.id.like)
		public void click(View view) {
			ImageView imageView = (ImageView) view;
		}
	}

}
