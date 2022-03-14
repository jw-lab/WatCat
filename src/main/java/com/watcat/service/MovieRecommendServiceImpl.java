package com.watcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.watcat.dto.reviewDto;
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
	//리뷰 등록
	@Override
	public void insertReview(reviewDto reviewDto) throws Exception {
		movieRecommendMapper.insertReview(reviewDto);
		
	}
	//리뷰 목록 -pageHelper 사용
	@Override
	public Page<reviewDto> selectReviewList(int pageNum) throws Exception {
		PageHelper.startPage(pageNum,10);
		return movieRecommendMapper.selectReviewList();
	}
	//리뷰 상세보기 페이지
	@Override
	public reviewDto getReviewDetail(int idx) throws Exception {

		return movieRecommendMapper.getReviewDetail(idx);
	}

}
