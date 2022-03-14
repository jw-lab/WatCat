package com.watcat.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;

@Mapper
public interface MypageMapper {

	void updatePw(userDto userdto) throws Exception;

	Page<reviewDto> MyreviewList(String userId) throws Exception;

	reviewDto reviewDetail(int idx) throws Exception;

}
