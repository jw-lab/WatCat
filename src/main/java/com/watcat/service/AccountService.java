package com.watcat.service;

import com.watcat.dto.userDto;

public interface AccountService {

	public int loginCheck(String userId, String userPw)throws Exception;

	public void signUp(userDto user) throws Exception;

	public int idCheck(String userId) throws Exception;

	
}
