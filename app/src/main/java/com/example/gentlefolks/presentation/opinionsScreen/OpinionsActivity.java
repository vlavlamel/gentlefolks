package com.example.gentlefolks.presentation.opinionsScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.base.BaseActivity;
import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionsGridFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class OpinionsActivity extends BaseActivity<OpinionUiEvent, OpinionUiModel> {

	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	private OpinionAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activty_opinions);
		ButterKnife.bind(this);
		RobotoTextView textView = mToolbar.findViewById(R.id.title);
		textView.setText(getIntent().getStringExtra(OpinionsGridFragment.HASHTAG_KEY));
		setSupportActionBar(mToolbar);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new OpinionAdapter();
		mRecyclerView.setAdapter(adapter);
		sendEvent(new OpinionUiEvent(getIntent().getStringExtra(OpinionsGridFragment.OPINION_ID_KEY)));
	}

	@Override
	protected Consumer<OpinionUiModel> getUIModelAction() {
		return new Consumer<OpinionUiModel>() {
			@Override
			public void accept(OpinionUiModel opinionUiModel) throws Exception {
				Opinion header = new Opinion(getIntent().getStringExtra(OpinionsGridFragment.IMAGE_KEY), getIntent().getStringExtra(OpinionsGridFragment.TITLE_KEY));
				opinionUiModel.getOpinionList()
					.add(0, header);
				adapter.setData(opinionUiModel.getOpinionList());
			}
		};
	}
}
