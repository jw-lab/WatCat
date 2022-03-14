package com.watcat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mypageMapper;
	
	@Override
	public void updatePw(userDto userdto) throws Exception {
		mypageMapper.updatePw(userdto);
	}

	@Override
	public Page<reviewDto> MyreviewList(int pageNum, String userId) throws Exception {
		PageHelper.startPage(pageNum, 9);
		return mypageMapper.MyreviewList(userId);
	}

	@Override
	public reviewDto reviewDetail(int idx) throws Exception {
		return mypageMapper.reviewDetail(idx);
	}

	@Override
	public void myReviewUpdate(reviewDto reviewdto) throws Exception {
		mypageMapper.myReviewUpdate(reviewdto);
	}

	@Override
	public void myReviewDelete(reviewDto reviewdto) throws Exception {
		mypageMapper.myReviewDelete(reviewdto);
	}

}
