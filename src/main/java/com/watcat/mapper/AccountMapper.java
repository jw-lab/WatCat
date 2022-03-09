package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.watcat.dto.userDto;

@Mapper
public interface AccountMapper {

	public int loginCheck(@Param("userId")String userId,@Param("userPw") String userPw)throws Exception;

	public void signUp(userDto user)throws Exception;

	public int idCheck(String userId)throws Exception;

	
}
