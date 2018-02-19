package com.example.gentlefolks.presentation.mainScreen.answerslist;

import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionMain;

import java.util.List;

public class AnswersFragmentUiModel {

	private List<Answer> answers;

	public AnswersFragmentUiModel(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getOpinionMains() {
		return answers;
	}

}
