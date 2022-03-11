package com.watcat.service;

import java.util.List;

import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

public interface MypageService {

	void updatePw(userDto userdto) throws Exception;

	List<reviewDto> MyreviewList(String userId) throws Exception;
}
