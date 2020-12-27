package com.wings.mywiki.intercepter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.OnlineService;

//���ͼ��� Ŭ������ ������� HandlerInterceptorAdapterŬ������ ����մϴ�.
public class LoginIntercepter extends HandlerInterceptorAdapter {
	@Autowired
	private OnlineService onlineService;
	//preHandle�� Ư�� ��Ʈ�ѷ��� �����ϱ� ���� ����ó���� ������ �ۼ�.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("로그인 인터셉터 ");
		
		Cookie[] cookie = request.getCookies();
		if(onlineService.Login(cookie[0].getValue())==1) {
			//로그인 됌

			return true;
			
		}
		else {
			return false;
		}
	}
}
	
	
	/*
	//postHandle�� ��Ʈ�ѷ��� ���� �� ����ó���� ������ �ۼ�.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	*/