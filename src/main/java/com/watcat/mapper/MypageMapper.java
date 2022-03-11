package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

@Mapper
public interface MypageMapper {

	void updatePw(userDto userdto) throws Exception;

	List<reviewDto> MyreviewList(String userId) throws Exception;

}
