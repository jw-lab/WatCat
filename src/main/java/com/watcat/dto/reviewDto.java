package com.watcat.dto;

import lombok.Data;

@Data
public class reviewDto {
	private int idx;
	private int movie_id;
	private String title;
	private String backdrop;
	private String poster;
	private String genres;
	private String actors;
	private String overview;
	private String tagline;
	private String release_date;
	private int runtime;
	private String user_id;
	private String content;
	private int rating;
}
