package com.watcat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watcat.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountMapper accountMapper;

	@Override
	public int loginCheck(String userId, String userPw) throws Exception {
		
		return accountMapper.loginCheck(userId,userPw);
	}
}
