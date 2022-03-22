package com.watcat.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class normalInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		Object adminPermission = session.getAttribute("adminPermission");
		Object userId = session.getAttribute("userId");

		//null값방지
		if (adminPermission==null) adminPermission=""; 
		
		
		//일반계정일경우
		if(userId!=null&&adminPermission.equals("N")) {
			
			//getWriter() 로 html 작성
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('비정상적인 접근 입니다'); location.href='/';</script>");
			 
			out.flush();
			
				
			return false;
		}
		
		return true;
		
	}
	
   @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    		@Nullable ModelAndView modelAndView) throws Exception {
      
    }
	
	
   @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
       
        
    }     


	
}
