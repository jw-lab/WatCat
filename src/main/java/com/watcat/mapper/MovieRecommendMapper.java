package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.dto.review.ReviewRecDto;

@Mapper
public interface MovieRecommendMapper {

	void insertMovieWish(MovieWishDto movieWish) throws Exception;

	void deleteMovieWish(MovieWishDto movieWish) throws Exception;

	List<MovieWishDto> selectMovieWish(MovieWishDto movieWish) throws Exception;

	void insertReview(reviewDto reviewDto) throws Exception;

	Page<reviewDto> selectReviewList() throws Exception;

	reviewDto getReviewDetail(int idx) throws Exception;

	void updateHitCnt(int idx) throws Exception;

	void insertReviewRec(ReviewRecDto reviewRec) throws Exception;

	void deleteReviewRec(ReviewRecDto reviewRec) throws Exception;

	List<ReviewRecDto> selectReviewRec(ReviewRecDto reviewRec) throws Exception;

	List<reviewDto> selectReviewRecList() throws Exception;

	List<reviewDto> selectRatingList() throws Exception;

}
