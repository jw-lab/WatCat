package com.watcat.service;

import java.util.List;

import com.watcat.dto.movie.MovieWishDto;

public interface MovieRecommendService {

	void insertMovieWish(MovieWishDto movieWish) throws Exception;

	void deleteMovieWish(MovieWishDto movieWish) throws Exception;

	List<MovieWishDto> selectMovieWish(MovieWishDto movieWish) throws Exception;

}
