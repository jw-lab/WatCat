package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.dto.movie.MovieWishDto;

@Mapper
public interface MypageMapper {

	void updatePw(userDto userdto) throws Exception;

	Page<reviewDto> MyreviewList(reviewDto reviewdto) throws Exception;

	reviewDto reviewDetail(int idx) throws Exception;

	void myReviewUpdate(reviewDto reviewdto) throws Exception;

	void myReviewDelete(reviewDto reviewdto) throws Exception;

	Page<reviewDto> MyreviewTrashList(String userId) throws Exception;

	//마이-휴지통 영구 삭제
	void MyreviewTrashDelete(int idx) throws Exception;

	//마이-휴지통 복구
	void MyreviewTrashRe(reviewDto reviewdto)throws Exception;

	// 마이-찜 리스트
	Page<reviewDto> myreviewWishList(String userId) throws Exception;

	List<MovieWishDto> MyreviewWishList(MovieWishDto movieWishDto) throws Exception;
	
}
