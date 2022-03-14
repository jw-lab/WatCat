package com.watcat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
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
	public ModelAndView mypageReview(HttpServletRequest httpServletRequest, @RequestParam(required=false, defaultValue = "1") int pageNum) throws Exception {
		ModelAndView mv = new ModelAndView("Mypage/MypageReview");
		HttpSession httpSession= httpServletRequest.getSession();
		String userId = httpSession.getAttribute("userId").toString();
		PageInfo<reviewDto> myreview = new PageInfo<reviewDto>(mypageService.MyreviewList(pageNum, userId), 10);
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
	
	// 리뷰 디테일
	@RequestMapping("mypage/reviewDetail")
	public ModelAndView reviewDetail(int idx) throws Exception{
		ModelAndView mv = new ModelAndView("MyPage/MypageReviewDetail");
		reviewDto reviewDetail = mypageService.reviewDetail(idx);
		mv.addObject("reviewDetail",reviewDetail);
		return mv;
	}
	
	// my리뷰 업데이트
	@RequestMapping("mypage/reviewUpdate")
	public String myReviewUpdate(reviewDto reviewdto) throws Exception {
		mypageService.myReviewUpdate(reviewdto);
		return "redirect:/mypage/review";
	}
	
	// my리뷰 삭제
	@RequestMapping("mypage/reviewDelete")
	public String myReviewDelete(reviewDto reviewdto) throws Exception{
		mypageService.myReviewDelete(reviewdto);
		return "redirect:/mypage/review";
	}
	
	
	// 리뷰trash 삭제
	
}