package com.example.gentlefolks.presentation.opinionsScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vl.melnikov on 19.11.17.
 */

public class OpinionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private final int ITEM_HEADER = 1;
	private final int ITEM_BASE = 0;

	private List<Opinion> listOpinions;

	public void setData(List<Opinion> listOpinions) {
		this.listOpinions = listOpinions;
		notifyDataSetChanged();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == ITEM_BASE) {
			View inflatedView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_opinion, parent, false);
			return new ViewHolder(inflatedView);
		} else {
			View inflatedView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_opinion_header, parent, false);
			return new HeaderViewHolder(inflatedView);
		}
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (getItemViewType(position) == ITEM_BASE) {
			((ViewHolder) holder).onBind(position);
		} else if (getItemViewType(position) == ITEM_HEADER){
			((HeaderViewHolder) holder).onBind(position);
		}
	}

	@Override
	public int getItemViewType(int position) {
		switch (listOpinions.get(position).itemType) {
			case 1:
				return ITEM_HEADER;
			case 0:
			default:
				return ITEM_BASE;
		}
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
			mAuthorsName.setText(opinion.authorName);
			mQuote.setText(opinion.quote);
			Picasso.with(mProfileImage.getContext())
				.load(opinion.profilePhoto)
				.into(mProfileImage, new Callback() {
					@Override
					public void onSuccess() {

					}

					@Override
					public void onError() {

					}
				});
			mQuantityLikes.setText("300");
			mAuthorPost.setText(opinion.authorPost);
		}
	}

	class HeaderViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.opinions_name)
		RobotoTextView mName;
		@BindView(R.id.opinions_picture)
		ImageView mPictures;

		public HeaderViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

		public void onBind(int position) {
			Opinion opinion = listOpinions.get(position);
			mName.setText(opinion.nameOpinion);
			Picasso.with(mPictures.getContext())
				.load(opinion.pictureOpinion)
				.into(mPictures, new Callback() {
					@Override
					public void onSuccess() {

					}

					@Override
					public void onError() {

					}
				});
		}
	}

}
