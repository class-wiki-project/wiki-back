package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.wings.mywiki.model.UserOutVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.FavService;
import com.wings.mywiki.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private FavService favService;
	
	private BCryptPasswordEncoder pwdEncoder;
	
	// 메인 페이지
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> main(HttpServletResponse response,
										HttpServletRequest request,
										HttpSession session) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		session = request.getSession();
		if(session.getAttribute("LOGIN")!=null) {
			UsersVO user = (UsersVO)session.getAttribute("LOGIN");
			UserOutVO put_user = new UserOutVO();
			put_user.setEmail(user.getEmail());
			put_user.setStudentName(user.getStudentName());
			put_user.setStudentNumber(user.getStudentNumber());
			put_user.setUnivName(user.getUnivName());
			put_user.setUserId(user.getUserId());
			map.put("user", put_user);
			map.put("favorite", favService.selectAll(user.getUserId()));
			
		}
		return map;
	}
	
	// 회원가입
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> userRegister(@RequestBody UsersVO userVO,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tmp = userVO.getPassword();
		System.out.println(tmp);
		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(tmp);
		System.out.println(pwd);
		
		userVO.setPassword(pwd);
		userService.insert(userVO);
		
		
		map.put("msg", "Success Register");
		return map;
	}	
	
	// Email Redundancy check
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> emailCheck(@RequestBody LoginVO loginVO,
				HttpServletResponse response) {
		System.out.println(loginVO.getEmail());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userService.checkId(loginVO.getEmail()) == 1) {
			map.put("msg", "Already exists Email");
			
		}
		else {
		map.put("msg", "Available EMAIL ID");
		}
		
		return map;
	}	
	//濡�洹몄�� 泥�由�
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody LoginVO loginVO,
			HttpServletResponse response, HttpSession session) throws IOException {
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		
		pwdEncoder = new BCryptPasswordEncoder();
		if(check != null) {
			if(pwdEncoder.matches(loginVO.getPassword(),check.getPassword())) {
			session.setAttribute("LOGIN", check);
			UserOutVO put_user = new UserOutVO();
			put_user.setEmail(check.getEmail());
			put_user.setStudentName(check.getStudentName());
			put_user.setStudentNumber(check.getStudentNumber());
			put_user.setUnivName(check.getUnivName());
			put_user.setUserId(check.getUserId());
			
			map.put("users", put_user);
			map.put("favorites", favService.selectAll(check.getUserId()));
			map.put("msg", "Login success");
			}
			else {
				map.put("msg", "ID and Password not match");
			}
		}
		
		return map;
	}	
	
	
	//Logout
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object>  logout(HttpSession session) {
		System.out.println("/user/logout accepted!");
		Map<String, Object> map = new HashMap<String, Object>();
		UsersVO user = (UsersVO) session.getAttribute("LOGIN");
		
		if(user != null) {
			session.removeAttribute("LOGIN");
			session.invalidate();
			map.put("msg", "Logout success");
		}
		
		return map;
		
	}
}
	
	
	
