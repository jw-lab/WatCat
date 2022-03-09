package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.watcat.dto.userDto;

@Mapper
public interface MypageMapper {

	void updatePw(userDto userdto) throws Exception;

}
