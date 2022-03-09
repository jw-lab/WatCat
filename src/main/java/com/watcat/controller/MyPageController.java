package com.watcat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.watcat.dto.userDto;
import com.watcat.service.MypageService;


@Controller
public class MyPageController {
	
	@Autowired
	MypageService mypageService;
	
	@RequestMapping("mypage")
	public String mypage() {
		return "Mypage/Mypage";
	}

	@RequestMapping("mypage/pw")
	public String mypagePw() {
		return "Mypage/MypagePW";
	}
	
	@RequestMapping("mypage/interested")
	public String mypageInterested() {
		return "Mypage/MypageInterested";
	}
	
	@RequestMapping("mypage/review")
	public String mypageReview() {
		return "Mypage/MypageReview";
	}
	
	@RequestMapping("mypage/trash")
	public String mypageTrash() {
		return "Mypage/MypageTrash";
	}
	
	@RequestMapping(value="mypage/updatepw", method = RequestMethod.POST)
	public String updatePw(HttpServletRequest httpServletRequest, userDto userdto ) throws Exception {
		
		HttpSession httpSession= httpServletRequest.getSession();
		userdto.setUserId(httpSession.getAttribute("userId").toString());
		mypageService.updatePw(userdto);
		return "Mypage/MyPage";
	}
}