package com.watcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<reviewDto> MyreviewList(String userId) throws Exception {
		return mypageMapper.MyreviewList(userId);
	}

}
