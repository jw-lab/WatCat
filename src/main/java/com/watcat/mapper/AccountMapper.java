package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

@Mapper
public interface AccountMapper {

	public int loginCheck(@Param("userId")String userId,@Param("userPw") String userPw)throws Exception;

	public void signUp(userDto user)throws Exception;

	public int idCheck(String userId)throws Exception;

	public Page<userDto> requestUserList() throws Exception;

	public void banUser(String userId)throws Exception;

	public void deleteUser(String userId)throws Exception;

	public void pardonUser(String userId)throws Exception;

	public int banCheck(String userId)throws Exception;

	public int getPermission(String userId)throws Exception;

	public Page<reviewDto> requestReviewList() throws Exception;

	public void permenentlyDeleteReview(int idx) throws Exception;

	public void deleteReview(int idx) throws Exception;

	public void repostReview(int idx) throws Exception;

	public void banReviewUser(String account) throws Exception;

	public Page<userDto> requestSearchUserList(String query)throws Exception;

	public Page<reviewDto> requestSearchReviewList(String query)throws Exception;

	
}
