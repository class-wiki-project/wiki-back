package com.wings.mywiki.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginIntercepter.class);
	
	  @Override 
	  public boolean preHandle(HttpServletRequest request,
	  HttpServletResponse response, Object handler) throws Exception {
	  
	HttpSession session = request.getSession();
	
	  if(session.getAttribute("loginSession") ==null){
		  logger.info("current user is not logged because there are no session");
		  response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401 Error 
		  return false; 
	  }
	  
	 return true; 
	 }
	  
	  
	 
}