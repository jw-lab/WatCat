package com.watcat.service;

import java.util.List;

import com.watcat.dto.userDto;

public interface AccountService {

	public int loginCheck(String userId, String userPw)throws Exception;

	public void signUp(userDto user) throws Exception;

	public int idCheck(String userId) throws Exception;

	public List<userDto> requestUserList() throws Exception;

	public void banUser(int idx) throws Exception;

	public void deleteUser(int idx) throws Exception;

	public void pardonUser(int idx) throws Exception;

	public int banCheck(String userId) throws Exception;

	
}
