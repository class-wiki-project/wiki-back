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
@CrossOrigin(origins="*", allowedHeaders="*")
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private FavService favService;
	
	private BCryptPasswordEncoder pwdEncoder;
	//메인 페이지
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json")
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
	//
	//회원가입
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> register(@RequestBody UsersVO userVO,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String tmp = userVO.getPassword();
		System.out.println(tmp);
		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(tmp);
		System.out.println(pwd);
		
		userVO.setPassword(pwd);
		userService.insert(userVO);
		
		
		map.put("msg", "회원가입이 완료되었습니다.");
		return map;
	}	
	
	//회원 가입시 id 중복 체크
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> emailCheck(@RequestParam String email,
			HttpServletResponse response) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		
		
		if(userService.checkId(email) == 1) {
			map.put("msg", "이미 존재하는 E-mail 입니다.");
			
		}
		else {
		map.put("msg", "사용가능한 아이디입니다.");
		}
		
		return map;
	}	
	//로그인 처리
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody LoginVO loginVO,
			HttpServletResponse response, HttpSession session) throws IOException {
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		
		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(loginVO.getPassword());
		System.out.println(pwd);
		System.out.println(check.getPassword());
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
			map.put("favorite", favService.selectAll(check.getUserId()));
			map.put("msg", "로그인이 되었습니다.");
			}
			else {
				map.put("msg", "아이디와 비밀번호가 일치하지 않습니다.");
			}
		}
		
		return map;
	}	
	
	
	//로그아웃 처리 요청.
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object>  logout(HttpSession session) {
		System.out.println("/user/logout 요청!");
		Map<String, Object> map = new HashMap<String, Object>();
		UsersVO user = (UsersVO) session.getAttribute("LOGIN");
		
		if(user != null) {
			session.removeAttribute("LOGIN");
			session.invalidate();
			map.put("msg", "로그아웃 되었습니다.");
		}
		
		return map;
		
	}
}
	
	
	
