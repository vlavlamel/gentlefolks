package com.example.gentlefolks.ui.mainScreen;

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

}
