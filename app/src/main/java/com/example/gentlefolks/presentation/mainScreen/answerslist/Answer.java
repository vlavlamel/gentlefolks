package com.example.gentlefolks.presentation.mainScreen.answerslist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by vl.melnikov on 19.02.2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer implements Serializable {
	public String answer;
	@JsonProperty("answer_title")
	public String answerTitle;
	@JsonProperty("author_name")
	public String authorName;
	@JsonProperty("author_post")
	public String authorPost;
	public String hashtag;
	@JsonProperty("answer_background")
	public String answerBkg;
	@JsonProperty("author_image")
	public String answerImage;
}
