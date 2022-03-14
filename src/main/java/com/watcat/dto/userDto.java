package com.watcat.dto;

import lombok.Data;

@Data
public class userDto {

	int idx;
	String userId;
	String userPw;
	String adminPermission;
	String deletedYn;
}
