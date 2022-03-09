package com.watcat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
