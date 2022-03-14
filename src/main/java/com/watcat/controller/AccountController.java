package com.watcat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		
		if(count==1) {
			HttpSession session= request.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("adminPermission", user.getAdminPermission());
			
			map.put("result", "success");
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
		session.removeAttribute("level");
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
	public ModelAndView requestUserList() throws Exception{
		ModelAndView mv = new ModelAndView("account/accountManagement");
		
		List<userDto> userList = new ArrayList<>();
		
		userList = accountService.requestUserList();
		
		mv.addObject("userList", userList);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/account",method=RequestMethod.PUT)
	public void banUser(@RequestParam("idx") int idx,@RequestParam("account") String account) throws Exception{
		
		if(account.equals("no")) {
			accountService.banUser(idx);
		}else{
			accountService.pardonUser(idx);
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/account",method=RequestMethod.DELETE)
	public void deleteUser(int idx) throws Exception{
		
		accountService.deleteUser(idx);
		
	}
}	