package com.watcat.service;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

public interface MypageService {

	void updatePw(userDto userdto) throws Exception;

	Page<reviewDto> MyreviewList(int pageNum, String userId) throws Exception;

	reviewDto reviewDetail(int idx) throws Exception;

	void myReviewUpdate(reviewDto reviewdto) throws Exception;

	void myReviewDelete(reviewDto reviewdto) throws Exception;
}
