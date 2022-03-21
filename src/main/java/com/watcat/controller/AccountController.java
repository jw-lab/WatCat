package com.watcat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.watcat.dto.reviewDto;
import com.watcat.dto.userDto;
import com.watcat.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	
	//로그인페이지
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage() {
		
		return "account/loginPage";
	}
	
	//로그인
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Object loginCheck(userDto user,HttpServletRequest request) throws Exception {
		
		Map<String,Object> map = new HashMap<>(); 
		
		int count = accountService.loginCheck(user.getUserId(),user.getUserPw());
		
		int ban = accountService.banCheck(user.getUserId());
		
		int check = accountService.getPermission(user.getUserId());
		String permission;
		
			if(check==1) {
				permission="Y";
			}else {
				permission="N";
			}
		
		if(count==1) {

			
			if(ban==1) {
				
				map.put("result", "banned");
				
			}else {
				
				HttpSession session= request.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("adminPermission", permission);
				
				map.put("result", "success");
				
			}
		}else {
			
			map.put("result", "error");
			
		}
		
		return map;
	}
	
	//로그아웃
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.DELETE)
	public void logOut(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("adminPermission");
		session.invalidate();
		
		
	}
	
	//회원가입 페이지
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signUpPage(){
		return "account/signUpPage";
	}
	
	//회원가입
	@ResponseBody
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public Object signUp(userDto user) throws Exception{
		
		Map<String,Object> map = new HashMap<>(); 
		
		int count = accountService.idCheck(user.getUserId());
		
		if(count==1) {
			
			map.put("result", "error");
			
		}else {
			accountService.signUp(user);
			
			map.put("result", "success");
		}
		
		return map;
		
		}
	

	//계정관리 페이지
	
	@RequestMapping(value="/admin/account",method=RequestMethod.GET)
	public ModelAndView requestUserList(@RequestParam(required = false,defaultValue = "1") int pageNum,@Nullable@RequestParam("query") String query) throws Exception{
		ModelAndView mv = new ModelAndView("account/admin/accountManagement");
		
		PageInfo<userDto> userList = new PageInfo<>(accountService.requestUserList(query,pageNum),10);
	
		mv.addObject("userList", userList);
		mv.addObject("query",query);
		
		return mv;
	}
	
	
	//계정 정지,해제
	@ResponseBody
	@RequestMapping(value="/admin/account",method=RequestMethod.PUT)
	public void banUser(@RequestParam("userId") String userId,@RequestParam("account") String account) throws Exception{
		
		if(account.equals("no")) {
			accountService.banUser(userId);
		}else{
			accountService.pardonUser(userId);
		}
		
	}
	
	//계정영구삭제
	@ResponseBody
	@RequestMapping(value="/admin/account",method=RequestMethod.DELETE)
	public void deleteUser(String userId) throws Exception{
		
		accountService.deleteUser(userId);
		
	}
	
	//리뷰관리페이지
	@ResponseBody
	@RequestMapping(value="/admin/review",method=RequestMethod.GET)
	public ModelAndView requestReviewList(@RequestParam(required = false,defaultValue = "1") int pageNum,@Nullable@RequestParam("query")String query) throws Exception{
		ModelAndView mv = new ModelAndView("account/admin/reviewManagement");
		PageInfo<reviewDto> reviewList = new PageInfo<>(accountService.requestReviewList(pageNum,query),5); 
		mv.addObject("reviewList", reviewList);
		mv.addObject("query",query);
		return mv;
	}
	
	//리뷰삭제
	
	@ResponseBody
	@RequestMapping(value="admin/review",method=RequestMethod.PUT)
	public void deleteReview(int idx,String deletedYn)throws Exception{
		if(deletedYn.equals("no")) {
			accountService.deleteReview(idx);
		}else{
			accountService.repostReview(idx);
		}
		
	}
	
	//리뷰 영구제거
	
	@ResponseBody
	@RequestMapping(value="admin/review",method=RequestMethod.DELETE)
	public void permanentlyDeleteReview(int idx)throws Exception{
		
		accountService.permenentlyDeleteReview(idx);
		
	}
	
	//계정 정지
	@ResponseBody
	@RequestMapping(value="admin/review/account",method=RequestMethod.PUT)
	public void banReviewUser(String account)throws Exception{
		
			accountService.banReviewUser(account);
		
		
	}
}	