package com.watcat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.service.MypageService;

@Controller
public class MyPageController {

	@Autowired
	MypageService mypageService;

//	// 마이페이지 첫페이지
//	@RequestMapping("mypage")
//	public String mypage() {
//		return "Mypage/Mypage";
//	}

	// 마이페이지 비밀번호
	@RequestMapping("mypage/pw")
	public ModelAndView mypagePw() {
		ModelAndView mv = new ModelAndView("MyPage/MypagePW");
		mv.addObject("pageName", "mypagePw");
		return mv;
	}

	// 마이페이지 wishlist 페이지
	@RequestMapping("mypage/wishlist")
	public ModelAndView mypageWishlist(HttpServletRequest httpServletRequest, 
			@RequestParam(required = false, defaultValue = "1") int pageNum, 
			@RequestParam(required = false, defaultValue = "") String title) throws Exception {
		ModelAndView mv = new ModelAndView("MyPage/MypageWishlist");
		HttpSession httpSession = httpServletRequest.getSession();
		MovieWishDto movieWishDto = new MovieWishDto();
		movieWishDto.setUserId(httpSession.getAttribute("userId").toString());
		mv.addObject("title", title);
		title = "%" + title + "%";
		movieWishDto.setTitle(title);
		PageInfo<MovieWishDto> wishList = new PageInfo<MovieWishDto>(mypageService.MyreviewWishList(pageNum, movieWishDto), 10);
		
		mv.addObject("wishList", wishList);
		mv.addObject("pageName", "mypageWishlist");
		return mv;
	}

	// 마이페이지 리뷰
	@RequestMapping("mypage/review")
	public ModelAndView mypageReview(HttpServletRequest httpServletRequest,
			@RequestParam(required = false, defaultValue = "1") int pageNum, 
			@RequestParam(required = false, defaultValue = "") String title) throws Exception {
		ModelAndView mv = new ModelAndView("MyPage/MypageReview");
		HttpSession httpSession = httpServletRequest.getSession();
		String userId = httpSession.getAttribute("userId").toString();
		reviewDto reviewdto = new reviewDto();
		reviewdto.setUserId(userId);
		mv.addObject("title", title);
		title = "%"+title+"%";
		reviewdto.setTitle(title);
		PageInfo<reviewDto> myreview = new PageInfo<reviewDto>(mypageService.MyreviewList(pageNum, reviewdto), 10);			
		mv.addObject("reviewList", myreview);
		mv.addObject("pageName", "mypageReview");
		return mv;
	}

	// 마이페이지 리뷰 휴지통
	@RequestMapping("mypage/trash")
	public ModelAndView mypageTrash(HttpServletRequest httpServletRequest,
			@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
		ModelAndView mv = new ModelAndView("MyPage/MypageTrash");
		HttpSession httpSession = httpServletRequest.getSession();
		String userId = httpSession.getAttribute("userId").toString();
		PageInfo<reviewDto> myreviewTrash = new PageInfo<reviewDto>(mypageService.MyreviewTrashList(pageNum, userId),
				10);
		
		List<reviewDto> cnt = mypageService.MyTrashCnt(userId);
		mv.addObject("trash", myreviewTrash);


		mv.addObject("cnt", cnt); 
		mv.addObject("pageName", "mypageTrash");
		
		return mv;
	}

	// 마이페이지 비밀번호 변경
	@RequestMapping(value = "mypage/updatepw", method = RequestMethod.POST)
	public String updatePw(HttpServletRequest httpServletRequest, userDto userdto) throws Exception {
		HttpSession httpSession = httpServletRequest.getSession();
		userdto.setUserId(httpSession.getAttribute("userId").toString());
		mypageService.updatePw(userdto);
		return "redirect:/mypage/pw";
	}

	// 리뷰 디테일
	@RequestMapping("mypage/reviewDetail")
	public ModelAndView reviewDetail(int idx) throws Exception {
		ModelAndView mv = new ModelAndView("MyPage/MypageReviewDetail");
		reviewDto reviewDetail = mypageService.reviewDetail(idx);
		mv.addObject("reviewDetail", reviewDetail);
		mv.addObject("pageName", "mypageReview");
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
	public String myReviewDelete(reviewDto reviewdto) throws Exception {
		mypageService.myReviewDelete(reviewdto);
		return "redirect:/mypage/review";
	}

	// 리뷰trash 삭제
	@RequestMapping(value = "mypage/trash/del/{idx}", method = RequestMethod.DELETE)
	public String mypageTrashDel(@PathVariable("idx") int idx) throws Exception {
		mypageService.MyreviewTrashDelete(idx);
		return "redirect:/mypage/trash";
	}
	
	
	// 리뷰trash 전체 삭제
	@RequestMapping(value = "mypage/trash/delAll", method = RequestMethod.DELETE)
	public String mypageTrashDelAll(HttpServletRequest httpServletRequest) throws Exception {
		
		HttpSession httpSession = httpServletRequest.getSession();
		String userId = httpSession.getAttribute("userId").toString();
		mypageService.MyreviewTrashDeleteAll(userId);
		
		
		return "redirect:/mypage/trash";
	}


	// 리뷰trash 리스트 출력
	@RequestMapping(value = "mypage/trash/del/{idx}", method = RequestMethod.GET)
	public String mypageTrashDelG(@PathVariable("idx") int idx) throws Exception {
		mypageService.MyreviewTrashDelete(idx);
		return "redirect:/mypage/trash";
	}

	// 리뷰 trash 복구
	@RequestMapping(value = "mypage/trash/re/{idx}", method = RequestMethod.PUT)
	public String mypageTrashRe(reviewDto reviewdto) throws Exception {
		mypageService.MyreviewTrashRe(reviewdto);
		return "redirect:/mypage/trash";
	}

	//
//	@RequestMapping(value = "mypage/trash/re/{idx}", method = RequestMethod.GET)
//	public String mypageTrashReG(reviewDto reviewdto) throws Exception {
//		mypageService.MyreviewTrashRe(reviewdto);
//		return "redirect:/mypage/trash";
//	}
//	
}