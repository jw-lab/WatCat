package com.watcat.dto.movie;

import lombok.Data;

@Data
public class MovieWishDto {
	private int movieId;
	private String userId;
	private String title;
	private String createdDt;

}
