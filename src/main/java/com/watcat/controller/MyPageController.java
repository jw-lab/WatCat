package com.watcat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.service.MypageService;


@Controller
public class MyPageController {
	
	@Autowired
	MypageService mypageService;
	
	//마이페이지 첫페이지
	@RequestMapping("mypage")
	public String mypage() {
		return "Mypage/Mypage";
	}
	
	//마이페이지 비밀번호
	@RequestMapping("mypage/pw")
	public String mypagePw() {
		return "Mypage/MypagePW";
	}
	
	//마이페이지 찜페이지
	@RequestMapping("mypage/interested")
	public String mypageInterested() {
		return "Mypage/MypageInterested";
	}
	
	//마이페이지 리뷰
	@RequestMapping("mypage/review")
	public ModelAndView mypageReview(HttpServletRequest httpServletRequest) throws Exception {
		ModelAndView mv = new ModelAndView("Mypage/MypageReview");
		HttpSession httpSession= httpServletRequest.getSession();
		String userId = httpSession.getAttribute("userId").toString();
		List<reviewDto> myreview =  mypageService.MyreviewList(userId);
		mv.addObject("reviewList", myreview);
		return mv;
	}
	
	//마이페이지 리뷰 휴지통
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