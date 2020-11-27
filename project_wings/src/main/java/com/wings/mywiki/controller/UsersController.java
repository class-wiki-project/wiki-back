package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.LoginVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.FavService;
import com.wings.mywiki.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private FavService favService;
	//���� ������
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> main(HttpServletResponse response,
										HttpServletRequest request,
										HttpSession session) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		session = request.getSession();
		if(session.getAttribute("LOGIN")!=null) {
			UsersVO user = (UsersVO)session.getAttribute("LOGIN");
			map.put("user", userService.selectOne(user.getUserId()));
			map.put("favorite", favService.selectAll(user.getUserId()));
			
		}
		return map;
	}	
	
	//ȸ������
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> register(@RequestBody UsersVO userVO,
			HttpServletResponse response) throws IOException {
		userService.insert(userVO);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�.");
		return map;
	}	
	
	//ȸ�� ���Խ� id �ߺ� üũ
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> emailCheck(@RequestParam String email,
			HttpServletResponse response) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.checkId(email) == 1) {
			map.put("msg", "�̹� �����ϴ� E-mail �Դϴ�.");
			
		}
		else {
		map.put("msg", "��밡���� ���̵��Դϴ�.");
		}
		
		return map;
	}	
	//�α��� ó��
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody LoginVO loginVO,
			HttpServletResponse response, HttpSession session) throws IOException {
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(check != null) {
			if(check.getPassword().equals(loginVO.getPassword())) {
			session.setAttribute("LOGIN", check);
			map.put("users", check);
			map.put("favorite", favService.selectAll(check.getUserId()));
			map.put("msg", "�α����� �Ǿ����ϴ�.");
			}
			else {
				map.put("msg", "���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		}
		
		return map;
	}	
	
	
	//�α׾ƿ� ó�� ��û.
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object>  logout(HttpSession session) {
		System.out.println("/user/logout ��û!");
		Map<String, Object> map = new HashMap<String, Object>();
		UsersVO user = (UsersVO) session.getAttribute("LOGIN");
		
		if(user != null) {
			session.removeAttribute("LOGIN");
			session.invalidate();
			map.put("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		}
		
		return map;
		
	}
	
}
