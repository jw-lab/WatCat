package com.watcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {
	@RequestMapping("mypage")
	public String mypage() {
		return "Mypage/MyPage";
	}

	@RequestMapping("mypage/pw")
	public String mypagePw() {
		return "Mypage/MyPagePW";
	}
	
	@RequestMapping("mypage/interested")
	public String mypageInterested() {
		return "Mypage/MyPageinterested";
	}
	
	@RequestMapping("mypage/review")
	public String mypageReview() {
		return "Mypage/MyPageReview";
	}
}