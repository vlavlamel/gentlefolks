package com.example.gentlefolks.presentation.opinionsScreen;

import java.util.List;

/**
 * Created by vl.melnikov on 25.11.2017.
 */

public class OpinionUiModel {

	private List<Opinion> mOpinionList;

	public OpinionUiModel(List<Opinion> opinionList) {
		mOpinionList = opinionList;
	}

	public List<Opinion> getOpinionList() {
		return mOpinionList;
	}
}
