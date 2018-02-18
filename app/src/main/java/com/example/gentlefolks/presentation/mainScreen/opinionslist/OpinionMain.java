package com.example.gentlefolks.presentation.mainScreen.opinionslist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpinionMain {

	@JsonProperty("hashtag")
	public String hashtag;
	@JsonProperty("opinion_background")
	public String opinionBackground;
	@JsonProperty("opinion_id")
	public String opinionId;
	@JsonProperty("opinion_title")
	public String opinionTitle;


}
