package com.watcat.service;

import java.util.List;

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

	@Override
	public List<userDto> requestUserList() throws Exception {
		
		return accountMapper.requestUserList();
	}

	@Override
	public void banUser(int idx) throws Exception {
	
		accountMapper.banUser(idx);
		
	}

	@Override
	public void deleteUser(int idx) throws Exception {
		
		accountMapper.deleteUser(idx);
		
	}

	@Override
	public void pardonUser(int idx) throws Exception {
		
		accountMapper.pardonUser(idx);
		
	}

	@Override
	public int banCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.banCheck(userId);
	}

	@Override
	public int getPermission(String userId) throws Exception {
		
		return accountMapper.getPermission(userId);
	}
}
