package com.example.gentlefolks.presentation.opinionsScreen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vl.melnikov on 19.11.17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Opinion {

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

}
