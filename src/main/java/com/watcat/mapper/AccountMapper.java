package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.watcat.dto.userDto;

@Mapper
public interface AccountMapper {

	public int loginCheck(@Param("userId")String userId,@Param("userPw") String userPw)throws Exception;

	public void signUp(userDto user)throws Exception;

	public int idCheck(String userId)throws Exception;

	public List<userDto> requestUserList()throws Exception;

	public void banUser(int idx)throws Exception;

	public void deleteUser(int idx)throws Exception;

	public void pardonUser(int idx)throws Exception;

	public int banCheck(String userId)throws Exception;

	public int getPermission(String userId)throws Exception;

	
}
