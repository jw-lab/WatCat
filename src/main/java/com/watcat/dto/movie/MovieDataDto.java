package com.watcat.dto.movie;


public class MovieDataDto {
	private String title;
	private String movieId;
	
	public MovieDataDto(String title, String movieId) {
		super();
		this.title = title;
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	
}
