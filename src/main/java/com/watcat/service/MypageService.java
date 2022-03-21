package com.watcat.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.dto.movie.MovieWishDto;

public interface MypageService {



	//비밀번호 변경
	void updatePw(userDto userdto) throws Exception; 

	//마이페이지 리뷰 리스트
	Page<reviewDto> MyreviewList(int pageNum, String userId) throws Exception;

	//마이페이지 리뷰 디테일
	reviewDto reviewDetail(int idx) throws Exception;

	//마이페이지 리뷰 수정
	void myReviewUpdate(reviewDto reviewdto) throws Exception;

	//마이페이지 리뷰 삭제
	void myReviewDelete(reviewDto reviewdto) throws Exception;

	// 휴지통 : 마이페이지 리뷰  리스트
	Page<reviewDto> MyreviewTrashList(int pageNum, String userId) throws Exception;

	// 휴지통:마이페이지 리뷰 영구 삭제	
	void MyreviewTrashDelete(int idx) throws Exception;

	// 휴지통:마이페이지 리뷰 복구 
	void MyreviewTrashRe(reviewDto reviewdto)throws Exception;

	// wish 리스트
	List<MovieWishDto> MyreviewWishList(MovieWishDto movieWishDto) throws Exception;


	


}
