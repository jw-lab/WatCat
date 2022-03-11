package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.watcat.dto.movie.MovieWishDto;

@Mapper
public interface MovieRecommendMapper {

	//MovieDataDto getMovieDetail(String title) throws Exception;

	void insertMovieWish(MovieWishDto movieWish) throws Exception;

	void deleteMovieWish(MovieWishDto movieWish) throws Exception;

}
