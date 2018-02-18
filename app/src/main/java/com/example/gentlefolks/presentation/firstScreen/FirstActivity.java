package com.example.gentlefolks.presentation.firstScreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.gentlefolks.R;
import com.example.gentlefolks.presentation.mainScreen.MainActivity;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;


public class FirstActivity extends AppCompatActivity {

	@BindView(R.id.button_enter)
	Button mEnterButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		ButterKnife.bind(this);
		RxView.clicks(mEnterButton)
			.subscribe(new Consumer<Object>() {
				@Override
				public void accept(Object aVoid) {
					startActivity(MainActivity.getStartIntent(getContext()));
				}
			});
	}

	private void showMessage(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT)
			.show();
	}

	private Context getContext() {
		return this;
	}
}
