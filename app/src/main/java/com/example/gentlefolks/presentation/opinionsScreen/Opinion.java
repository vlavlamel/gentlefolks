package com.example.gentlefolks.presentation.opinionsScreen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by vl.melnikov on 19.11.17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Opinion implements Serializable {

	@JsonProperty("author_name")
	public String authorName;
	@JsonProperty("author_post")
	public String authorPost;
	@JsonProperty("opinion")
	public String opinion;
	@JsonProperty("profile_photo")
	public String profilePhoto;
	@JsonProperty("quote")
	public String quote;
	public String likes;

	public transient int itemType;
	public transient String pictureOpinion;
	public transient String nameOpinion;

	public Opinion() {
	}

	public Opinion(String pictureOpinion, String nameOpinion) {
		this.pictureOpinion = pictureOpinion;
		this.nameOpinion = nameOpinion;
		itemType = 1;
	}
}
