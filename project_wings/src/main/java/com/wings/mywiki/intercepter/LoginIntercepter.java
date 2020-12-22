package com.wings.mywiki.intercepter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import com.wings.mywiki.model.UsersVO;

//���ͼ��� Ŭ������ ������� HandlerInterceptorAdapterŬ������ ����մϴ�.
public class LoginIntercepter extends HandlerInterceptorAdapter {

	//preHandle�� Ư�� ��Ʈ�ѷ��� �����ϱ� ���� ����ó���� ������ �ۼ�.
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
	//postHandle�� ��Ʈ�ѷ��� ���� �� ����ó���� ������ �ۼ�.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	*/