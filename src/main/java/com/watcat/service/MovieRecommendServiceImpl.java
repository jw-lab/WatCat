package com.watcat.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.watcat.dto.reviewDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.dto.review.ReviewRecDto;
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
	public HashMap<Object, Object> getReviewDetail(int idx) throws Exception {
		HashMap<Object, Object> review = new HashMap<>();
		
		reviewDto reviewData = movieRecommendMapper.getReviewDetail(idx);
		review.put("idx", reviewData.getIdx());
		review.put("movieId", reviewData.getMovieId());
		review.put("title", reviewData.getTitle());
		review.put("poster", reviewData.getPoster());
		review.put("genres", reviewData.getGenres());
		review.put("actors", reviewData.getActors());
		review.put("overview", reviewData.getOverview());
		review.put("tagline", reviewData.getTagline());
		review.put("releaseDate", reviewData.getReleaseDate());
		review.put("runtime", reviewData.getRuntime());
		review.put("userId", reviewData.getUserId());
		review.put("content", reviewData.getContent());
		review.put("rating", reviewData.getRating());
		review.put("createdDt", reviewData.getCreatedDt());
		review.put("reviewRecommend", reviewData.getReviewRecommend());
		review.put("hitCnt", reviewData.getHitCnt());
		return review;
	}
	
	//추천하기
	@Override
	public void insertReviewRec(ReviewRecDto reviewRec) throws Exception {
		movieRecommendMapper.insertReviewRec(reviewRec);
		
	}
	//추천취소
	@Override
	public void deleteReviewRec(ReviewRecDto reviewRec) throws Exception {
		movieRecommendMapper.deleteReviewRec(reviewRec);
		
	}
	//나의 추천 목록
	@Override
	public List<ReviewRecDto> selectReviewRec(ReviewRecDto reviewRec) throws Exception {
		
		return movieRecommendMapper.selectReviewRec(reviewRec);
	}
	
	//추천수 순으로 정렬
	@Override
	public List<reviewDto> selectReviewRecList() throws Exception {
		return movieRecommendMapper.selectReviewRecList();
	}
	//조회수 증가
	@Override
	public void updateHitCnt(int idx) throws Exception {
		movieRecommendMapper.updateHitCnt(idx);
		
	}

}
