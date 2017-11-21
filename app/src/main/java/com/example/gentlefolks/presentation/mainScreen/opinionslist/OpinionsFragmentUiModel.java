package com.example.gentlefolks.presentation.mainScreen.opinionslist;

import java.util.List;

public class OpinionsFragmentUiModel {

	private List<OpinionMain> mOpinionMains;

	public OpinionsFragmentUiModel(List<OpinionMain> opinionMains) {
		mOpinionMains = opinionMains;
	}

	public List<OpinionMain> getOpinionMains() {
		return mOpinionMains;
	}

}
