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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vl.melnikov on 19.11.17.
 */

public class OpinionsActivity extends AppCompatActivity {

	@BindView(R.id.opinions_name)
	RobotoTextView mOpinionsName;
	@BindView(R.id.opinions_picture)
	ImageView mOpinionsPicture;
	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activty_opinions);
		ButterKnife.bind(this);
		RobotoTextView textView = mToolbar.findViewById(R.id.title);
		textView.setText("#искусство");
		setSupportActionBar(mToolbar);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		OpinionAdapter adapter = new OpinionAdapter();
		mRecyclerView.setAdapter(adapter);
		List<Opinion> opinions = new ArrayList<>();
		Opinion opinion1 = new Opinion("Антон Долин", getString(R.string.quote), R.drawable.anton, "журналист, кинокритик", "300");
		Opinion opinion2 = new Opinion("сукин сынович", "дохуя дохуя дохуя текста дохуя дохуя дохуя текста дохуя дохуя дохуя текста дохуя дохуя дохуя текстадохуя дохуя дохуя текста ", R.drawable.ic_group, "пидар", "-300");
		opinions.add(opinion1);
		opinions.add(opinion2);
		adapter.setData(opinions);
	}
}
