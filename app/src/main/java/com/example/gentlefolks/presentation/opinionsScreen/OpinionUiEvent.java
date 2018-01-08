package com.example.gentlefolks.presentation.opinionsScreen;

/**
 * Created by vl.melnikov on 25.11.2017.
 */

public class OpinionUiEvent {

	public String getOpinion_id() {
		return opinion_id;
	}

	private String opinion_id;

	public OpinionUiEvent(String opinion_id) {
		this.opinion_id = opinion_id;
	}
}
