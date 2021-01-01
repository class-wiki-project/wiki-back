package com.wings.mywiki.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.wings.mywiki.model.UsersVO;

public class LoginIntercepter extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginIntercepter.class);
	
	  @Override 
	  public boolean preHandle(HttpServletRequest request,
	  HttpServletResponse response, Object handler) throws Exception {
	  
	  if((UsersVO)WebUtils.getSessionAttribute(request, "loginSession") ==null){
		  logger.info("current user is not logged because there are no session");
		  response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401 Error 
		  return false; 
	  }
	  UsersVO USER = (UsersVO)WebUtils.getSessionAttribute(request, "loginSession");
	  logger.info(USER.toString());
	  
	 return true; 
	 }
}