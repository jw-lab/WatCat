package com.watcat.dto;

import lombok.Data;

@Data
public class reviewDto {
	private int idx;
	private int movieId;
	private String title;
	private String backdrop;
	private String poster;
	private String genres;
	private String actors;
	private String overview;
	private String tagline;
	private String releaseDate;
	private int runtime;
	private String userId;
	private String content;
	private int rating;
	private String deletedYn;
	private String createdDt;
}
