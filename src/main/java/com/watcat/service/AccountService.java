package com.watcat.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

public interface AccountService {

	public int loginCheck(String userId, String userPw)throws Exception;

	public void signUp(userDto user) throws Exception;

	public int idCheck(String userId) throws Exception;

	public List<userDto> requestUserList() throws Exception;

	public void banUser(String userId) throws Exception;

	public void deleteUser(String userId) throws Exception;

	public void pardonUser(String userId) throws Exception;

	public int banCheck(String userId) throws Exception;

	public int getPermission(String userId) throws Exception;

	public Page<reviewDto> requestReviewList(int pageNum) throws Exception;

	public void deleteReview(int idx)throws Exception;

	public void permenentlyDeleteReview(int idx)throws Exception;

	public void repostReview(int idx)throws Exception;

	public void banReviewUser(String account)throws Exception;

	
}
