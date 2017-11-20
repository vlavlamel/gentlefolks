package com.example.gentlefolks.ui.opinionsScreen;

/**
 * Created by vl.melnikov on 19.11.17.
 */

public class Opinion {

	private String author;

	private String opinion;

	private int image_profile;

	private String post;

	private String likes;

	public Opinion(String author, String opinion, int image_profile, String post, String likes) {
		this.author = author;
		this.opinion = opinion;
		this.image_profile = image_profile;
		this.post = post;
		this.likes = likes;
	}

	public String getAuthor() {
		return author;
	}

	public String getOpinion() {
		return opinion;
	}

	public int getImage_profile() {
		return image_profile;
	}

	public String getPost() {
		return post;
	}

	public String getLikes() {
		return likes;
	}
}
