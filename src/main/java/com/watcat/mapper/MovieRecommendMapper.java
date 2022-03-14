package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.watcat.dto.movie.MovieWishDto;

@Mapper
public interface MovieRecommendMapper {

	void insertMovieWish(MovieWishDto movieWish) throws Exception;

	void deleteMovieWish(MovieWishDto movieWish) throws Exception;

	List<MovieWishDto> selectMovieWish(MovieWishDto movieWish) throws Exception;

}
