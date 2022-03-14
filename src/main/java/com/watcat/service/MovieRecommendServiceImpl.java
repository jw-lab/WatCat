package com.watcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watcat.dto.movie.MovieWishDto;
import com.watcat.mapper.MovieRecommendMapper;

@Service
public class MovieRecommendServiceImpl implements MovieRecommendService {
	
	@Autowired
	private MovieRecommendMapper movieRecommendMapper;
	//찜하기 추가
	@Override
	public void insertMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendMapper.insertMovieWish(movieWish);
	}
	//찜하기 해제
	@Override
	public void deleteMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendMapper.deleteMovieWish(movieWish);
	}
	//찜하기 list 정보
	@Override
	public List<MovieWishDto> selectMovieWish(MovieWishDto movieWish) throws Exception {
		return movieRecommendMapper.selectMovieWish(movieWish);
	}

}
