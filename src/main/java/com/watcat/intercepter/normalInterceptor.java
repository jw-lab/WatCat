package com.watcat.intercepter;

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
		
		Object userId = session.getAttribute("userId");
		
		if(userId==null) {
			response.sendRedirect("/login");
			
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
