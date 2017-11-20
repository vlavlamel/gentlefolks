package com.example.gentlefolks.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.gentlefolks.R;


/**
 * Created by vl.melnikov on 22.10.17.
 */

public class AnimatedStateLayout extends ViewAnimator {

	private static final String TAG = AnimatedStateLayout.class.getSimpleName();

	private final static int UNKNOWN_ID = -1;
	private final static int STATE_DATA_LAYOUT = 0;
	private final static int STATE_EMPTY_LAYOUT = 1;
	private final static int STATE_PROGRESS_LAYOUT = 2;
	private final static int STATE_ERROR_LAYOUT = 3;

	private int defaultProgressLayoutRes = R.layout.state_layout_default_progress;
	private int defaultErrorLayoutRes = R.layout.state_layout_default_error;

	private int customEmptyLayoutRes = UNKNOWN_ID;
	private int customProgressLayoutRes = UNKNOWN_ID;
	private int customErrorLayoutRes = UNKNOWN_ID;

	private int dataLayoutId = UNKNOWN_ID;
	private int progressLayoutId = UNKNOWN_ID;
	private int emptyLayoutId = UNKNOWN_ID;
	private int errorLayoutId = UNKNOWN_ID;

	private int currentLayout = UNKNOWN_ID;

	public AnimatedStateLayout(Context context) {
		this(context, null);
	}

	public AnimatedStateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimatedStateLayout);
		if (a.hasValue(R.styleable.AnimatedStateLayout_custom_empty)) {
			customEmptyLayoutRes = a.getInt(R.styleable.AnimatedStateLayout_custom_empty, defaultProgressLayoutRes);
		}
		if (a.hasValue(R.styleable.AnimatedStateLayout_custom_progress)) {
			customProgressLayoutRes = a.getInt(R.styleable.AnimatedStateLayout_custom_progress, defaultProgressLayoutRes);
		}
		if (a.hasValue(R.styleable.AnimatedStateLayout_custom_error)) {
			customErrorLayoutRes = a.getInt(R.styleable.AnimatedStateLayout_custom_error, defaultErrorLayoutRes);
		}
		a.recycle();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			int viewType = ((LayoutParams) child.getLayoutParams()).viewType;
			switch (viewType) {
				case STATE_DATA_LAYOUT:
					dataLayoutId = i;
					break;
				case STATE_EMPTY_LAYOUT:
					emptyLayoutId = i;
					break;
				case STATE_PROGRESS_LAYOUT:
					progressLayoutId = i;
					break;
				case STATE_ERROR_LAYOUT:
					errorLayoutId = i;
					break;
			}
		}
		if (isInEditMode()) {
			showData();
		} else {
			showProgress();
		}
	}

	public void showData() {
		if (dataLayoutId != UNKNOWN_ID) {
			currentLayout = STATE_DATA_LAYOUT;
			setDisplayedChild(dataLayoutId);
		}
	}

	public void showEmpty() {
		if (emptyLayoutId == UNKNOWN_ID) {
			View child = LayoutInflater
				.from(this.getContext())
				.inflate(customEmptyLayoutRes != UNKNOWN_ID ? customEmptyLayoutRes : defaultProgressLayoutRes, null);
			LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.viewType = STATE_EMPTY_LAYOUT;
			lp.gravity = Gravity.CENTER;
			child.setLayoutParams(lp);
			emptyLayoutId = getChildCount();
			addView(child);
		}
		currentLayout = STATE_EMPTY_LAYOUT;
		setDisplayedChild(emptyLayoutId);
	}

	public void showProgress() {
		if (progressLayoutId == UNKNOWN_ID) {
			View child = LayoutInflater
				.from(this.getContext())
				.inflate(customProgressLayoutRes != UNKNOWN_ID ? customProgressLayoutRes : defaultProgressLayoutRes, null);
			LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.viewType = STATE_PROGRESS_LAYOUT;
			lp.gravity = Gravity.CENTER;
			child.setLayoutParams(lp);
			progressLayoutId = getChildCount();
			addView(child);
		}
		currentLayout = STATE_PROGRESS_LAYOUT;
		setDisplayedChild(progressLayoutId);
	}

	public void showDefaultError() {
		showDefaultErrorWithText(R.string.errorServerUnavailable);
	}

	public void showDefaultErrorWithText(int errorRes) {
		this.showDefaultErrorWithText(getResources().getString(errorRes));
	}

	public void showDefaultErrorWithText(String errorText) {
		showCustomErrorWithText(errorText, R.id.progress_view_error);
	}

	public void showCustomErrorWithText(String errorText, int errorResId) {
		showError();
		TextView errorTv = (TextView) getChildAt(errorLayoutId).findViewById(errorResId);
		if (errorTv != null) {
			errorTv.setVisibility(VISIBLE);
			errorTv.setText(errorText);
		}
	}

	public void showDefaultErrorWithoutText() {
		showError();
		TextView errorTv = (TextView) getChildAt(errorLayoutId).findViewById(R.id.progress_view_error);
		if (errorTv != null) {
			errorTv.setVisibility(GONE);
		}
	}

	public void showError() {
		if (errorLayoutId == UNKNOWN_ID) {
			View child = LayoutInflater
				.from(this.getContext())
				.inflate(customProgressLayoutRes != UNKNOWN_ID ? customErrorLayoutRes : defaultErrorLayoutRes, null);
			LayoutParams lp = new LayoutParams();
			lp.viewType = STATE_ERROR_LAYOUT;
			child.setLayoutParams(lp);
			errorLayoutId = getChildCount();
			addView(child);
		}
		currentLayout = STATE_ERROR_LAYOUT;
		setDisplayedChild(errorLayoutId);
	}

	public View getErrorLayout() {
		return errorLayoutId != UNKNOWN_ID ? getChildAt(errorLayoutId) : null;
	}

	// ----------------------- Custom Layout Params -----------------------
	@Override
	public ViewAnimator.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new AnimatedStateLayout.LayoutParams(getContext(), attrs);
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof AnimatedStateLayout.LayoutParams;
	}

	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
		return new AnimatedStateLayout.LayoutParams(lp);
	}

	@Override
	protected ViewAnimator.LayoutParams generateDefaultLayoutParams() {
		return new AnimatedStateLayout.LayoutParams();
	}

	public static class LayoutParams extends ViewAnimator.LayoutParams {
		int viewType = UNKNOWN_ID;

		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimatedStateLayout);
			if (a.hasValue(R.styleable.AnimatedStateLayout_viewType)) {
				viewType = a.getInt(R.styleable.AnimatedStateLayout_viewType, UNKNOWN_ID);
			}
			a.recycle();
		}

		public LayoutParams(ViewGroup.LayoutParams params) {
			super(params);
			if (params instanceof LayoutParams) {
				LayoutParams layoutParams = (LayoutParams) params;
				viewType = layoutParams.viewType;
			}
		}

		public LayoutParams() {
			this(MATCH_PARENT, MATCH_PARENT);
		}

		public LayoutParams(int width, int height) {
			super(width, height);
		}
	}

	public boolean isDataLayoutActive() {
		return currentLayout == STATE_DATA_LAYOUT;
	}

	public boolean isEmptyLayoutActive() {
		return currentLayout == STATE_EMPTY_LAYOUT;
	}
}
