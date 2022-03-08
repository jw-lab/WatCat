package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

	public int loginCheck(@Param("userId")String userId,@Param("userPw") String userPw)throws Exception;

	
}
