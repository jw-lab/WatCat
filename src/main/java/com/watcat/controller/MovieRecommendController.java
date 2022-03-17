package com.watcat.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.watcat.dto.reviewDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.dto.review.ReviewRecDto;
import com.watcat.service.MovieRecommendService;

@Controller
public class MovieRecommendController {

	@Autowired
	private MovieRecommendService movieRecommendService;
	
	//추천영화 페이지
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView movieRecommend(@RequestParam(required = false,defaultValue = "1") int pageNum) throws Exception{
		ModelAndView mv = new ModelAndView("movie/recommend");
		// 최신순 목록
		PageInfo<reviewDto> reviewList = new PageInfo<>(movieRecommendService.selectReviewList(pageNum),5);
		
		// 추천순 목록
		List<reviewDto> reviewRecList = movieRecommendService.selectReviewRecList();
		mv.addObject("reviewList", reviewList);
		mv.addObject("reviewRecList",reviewRecList);
		return mv;
	}
	
	//영화 리뷰 상세보기 페이지 이동
	@RequestMapping(value="/review/detail", method = RequestMethod.GET)
	public ModelAndView reviewDetail(int idx, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("movie/reviewDetail");
		mv.addObject("idx", idx);
		
		//조회수 중복 방지
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies !=null) {
			for(Cookie cookie:cookies) {
				if (cookie.getName().equals("reviewBoard")) {
					oldCookie = cookie;
				}
			}
		}
		
		if (oldCookie != null) {
			if (!oldCookie.getValue().contains("["+idx+"]")) {
				movieRecommendService.updateHitCnt(idx);
				oldCookie.setValue(oldCookie.getValue()+ "["+idx+"]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60*60*24);
				response.addCookie(oldCookie);
			}
		} else {
			movieRecommendService.updateHitCnt(idx);
			Cookie newCookie = new Cookie("reviewBoard", "["+idx+"]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60*60*24);
			response.addCookie(newCookie);
		}
		return mv;
	}
	//영화 리뷰 상세페이지 데이터 ajax
	@ResponseBody
	@RequestMapping(value = "/review/detail/data", method= RequestMethod.GET)
	public Object reviewDetailData(int idx) throws Exception {
		HashMap<Object, Object> review = movieRecommendService.getReviewDetail(idx);
		return review;
	}
	
	//리뷰 추천 추가
	@ResponseBody
	@RequestMapping(value = "/review/detail/rec", method = RequestMethod.POST)
	public void insertReviewRec(ReviewRecDto reviewRec) throws Exception {
		movieRecommendService.insertReviewRec(reviewRec);
	}
	//리뷰 추천 해제
	@ResponseBody
	@RequestMapping(value = "/review/detail/rec", method = RequestMethod.DELETE)
	public void deleteReviewRec(ReviewRecDto reviewRec) throws Exception {
		movieRecommendService.deleteReviewRec(reviewRec);
	}
	//리뷰 추천 list 정보
	@ResponseBody
	@RequestMapping(value = "/review/detail/rec", method = RequestMethod.GET)
	public Object selectReviewRec(ReviewRecDto reviewRec) throws Exception {
		List<ReviewRecDto> reviewRecList= movieRecommendService.selectReviewRec(reviewRec);
		return reviewRecList;
	}
	
	
	//영화 상세보기 페이지 이동
	@RequestMapping(value="/movie/detail", method = RequestMethod.GET)
	public String movieDetail(int movieId, Model model) throws Exception {
		model.addAttribute("movieId", movieId);
		return "movie/detail";
	}

	
	//영화 검색 페이지
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String movieSearch() throws Exception {
		return "movie/search";
	}
	
	//찜하기 추가
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.POST)
	public void insertMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendService.insertMovieWish(movieWish);
	}
	//찜하기 해제
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.DELETE)
	public void deleteMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendService.deleteMovieWish(movieWish);
	}
	//찜하기 list 정보
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.GET)
	public Object selectMovieWish(MovieWishDto movieWish) throws Exception {
		List<MovieWishDto> movieWishList = movieRecommendService.selectMovieWish(movieWish);
		return movieWishList;
	}
	
	//리뷰 등록 post
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String insertReview(reviewDto reviewDto) throws Exception {
		movieRecommendService.insertReview(reviewDto);
		return "redirect:/";
	}
}
