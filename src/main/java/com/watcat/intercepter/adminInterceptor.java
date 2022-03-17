package com.watcat.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class adminInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		
		Object adminPermission = session.getAttribute("adminPermission");
		
		if(adminPermission==null||adminPermission.equals("N")) {
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