package com.watcat.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.movie.MovieWishDto;

public interface MovieRecommendService {

	void insertMovieWish(MovieWishDto movieWish) throws Exception;

	void deleteMovieWish(MovieWishDto movieWish) throws Exception;

	List<MovieWishDto> selectMovieWish(MovieWishDto movieWish) throws Exception;

	void insertReview(reviewDto reviewDto) throws Exception;

	Page<reviewDto> selectReviewList(int pageNum) throws Exception;

	reviewDto getReviewDetail(int idx) throws Exception;

}
