package com.watcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mypageMapper;
	
	@Override //비밀번호 변
	public void updatePw(userDto userdto) throws Exception {
		mypageMapper.updatePw(userdto);
	}

	@Override //마이페이지 리뷰 리스트
	public Page<reviewDto> MyreviewList(int pageNum, String userId) throws Exception {
		PageHelper.startPage(pageNum, 12);
		return mypageMapper.MyreviewList(userId);
	}

	@Override //마이페이지 리뷰 디테일
	public reviewDto reviewDetail(int idx) throws Exception {
		return mypageMapper.reviewDetail(idx);
	}

	@Override // 마이페이지 리뷰 업데이트
	public void myReviewUpdate(reviewDto reviewdto) throws Exception {
		mypageMapper.myReviewUpdate(reviewdto);
	}

	@Override //마이페이지 리뷰 삭제
	public void myReviewDelete(reviewDto reviewdto) throws Exception {
		mypageMapper.myReviewDelete(reviewdto);
	}

	@Override //마이페이지 리뷰 휴지통 리스트
	public Page<reviewDto> MyreviewTrashList(int pageNum, String userId) throws Exception {
		PageHelper.startPage(pageNum, 10);
		return mypageMapper.MyreviewTrashList(userId);
	}

	@Override // 마이페이지 리뷰 영구 삭제	
	public void MyreviewTrashDelete(int idx) throws Exception {
		mypageMapper.MyreviewTrashDelete(idx);
		
	}

	@Override // 휴지통 리뷰 복구	
	public void MyreviewTrashRe(reviewDto reviewdto) throws Exception {
		mypageMapper.MyreviewTrashRe(reviewdto);
		
	}

	@Override //wish 리스트
	public List<MovieWishDto> MyreviewWishList(MovieWishDto movieWishDto) throws Exception {
		return mypageMapper.MyreviewWishList(movieWishDto);
	}


}
