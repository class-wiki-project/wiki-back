package com.wings.mywiki.intercepter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import com.wings.mywiki.model.UsersVO;

//인터셉터 클래스를 만드려면 HandlerInterceptorAdapter클래스를 상속합니다.
public class LoginIntercepter extends HandlerInterceptorAdapter {

	//preHandle은 특정 컨트롤러에 진입하기 전에 공통처리할 내용을 작성.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
        UsersVO loginVO = (UsersVO) session.getAttribute("LOGIN");

        if(loginVO == null){
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        return true;
	}
}
	
	
	/*
	//postHandle은 컨트롤러를 나갈 때 공통처리할 내용을 작성.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	*/