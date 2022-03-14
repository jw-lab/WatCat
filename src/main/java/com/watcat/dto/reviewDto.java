package com.watcat.dto;

import lombok.Data;

@Data
public class reviewDto {
	int idx;
	int movieId;
	String title;
	String backdrop;
	String poster;
	String genres;
	String actors;
	String overview;
	String tagline;
	String releaseDate;
	int runtime;
	String userId;
	String content;
	int rating;
	String deletedYn;
	String createdDt;
}
