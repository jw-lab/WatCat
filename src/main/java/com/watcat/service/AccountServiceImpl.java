package com.watcat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.watcat.dto.reviewDto;
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
	public Page<userDto> requestUserList(@Nullable String query,int pageNum) throws Exception {
		
		PageHelper.startPage(pageNum,10);
		if(query==null) {
			return accountMapper.requestUserList();
		}else {
			return accountMapper.requestSearchUserList(query);
		}
	}
	

	@Override
	public void banUser(String userId) throws Exception {
	
		accountMapper.banUser(userId);
		
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		
		accountMapper.deleteUser(userId);
		
	}

	@Override
	public void pardonUser(String userId) throws Exception {
		
		accountMapper.pardonUser(userId);
		
	}

	@Override
	public int banCheck(String userId) throws Exception {
		
		return accountMapper.banCheck(userId);
	}

	@Override
	public int getPermission(String userId) throws Exception {
		
		return accountMapper.getPermission(userId);
	}

	@Override
	public Page<reviewDto> requestReviewList(int pageNum,String query) throws Exception {
		PageHelper.startPage(pageNum,4);
		if(query==null) {
			return accountMapper.requestReviewList();
		}else {
			return accountMapper.requestSearchReviewList(query);
		}
	}

	@Override
	public void deleteReview(int idx) throws Exception {
		
		accountMapper.deleteReview(idx);
		
	}

	@Override
	public void repostReview(int idx) throws Exception {
	
		accountMapper.repostReview(idx);
		
	}
	
	@Override
	public void permenentlyDeleteReview(int idx) throws Exception {
		
		accountMapper.permenentlyDeleteReview(idx);
		
	}

	@Override
	public void banReviewUser(String account) throws Exception {
		
		accountMapper.banReviewUser(account);
		
	}

}
