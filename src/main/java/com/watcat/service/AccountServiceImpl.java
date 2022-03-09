package com.watcat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watcat.dto.userDto;
import com.watcat.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountMapper accountMapper;

	@Override
	public int loginCheck(String userId, String userPw) throws Exception {
		
		return accountMapper.loginCheck(userId,userPw);
	}

	@Override
	public void signUp(userDto user) throws Exception {
		
		accountMapper.signUp(user);
		
	}

	@Override
	public int idCheck(String userId) throws Exception {
		
		return accountMapper.idCheck(userId);
		
	}
}
