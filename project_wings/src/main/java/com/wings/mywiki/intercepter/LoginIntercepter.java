package com.wings.mywiki.intercepter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wings.mywiki.model.UsersVO;

//���ͼ��� Ŭ������ ������� HandlerInterceptorAdapterŬ������ ����մϴ�.
public class LoginIntercepter extends HandlerInterceptorAdapter {

	//preHandle�� Ư�� ��Ʈ�ѷ��� �����ϱ� ���� ����ó���� ������ �ۼ�.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("로그인 인터셉터 ");
		Cookie[] cookie = request.getCookies();
		for(int i=0; i<cookie.length; i++) {
			System.out.println(i + "번째 쿠키 이름 " + cookie[i].getName());
			System.out.println(i + "번째 쿠키 값 " + cookie[i].getValue());
		}
		return true;
		
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