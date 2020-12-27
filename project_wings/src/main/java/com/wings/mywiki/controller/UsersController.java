package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wings.mywiki.dao.mybatis.mapper.OnlineMapper;
import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.LoginVO;
import com.wings.mywiki.model.OnlinVO;
import com.wings.mywiki.model.UserOutVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.FavService;
import com.wings.mywiki.service.OnlineService;
import com.wings.mywiki.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private FavService favService;
	@Autowired
	private OnlineService onlineService;
	
	private BCryptPasswordEncoder pwdEncoder;
	//메인 페이지
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> main(HttpServletResponse response,
										HttpServletRequest request,
										HttpSession session) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();

		Cookie[] cookie = request.getCookies();
		OnlinVO push = onlineService.select(cookie[0].getValue());
		
		
		//1) 쿠키를 통해 해당유저 파악필요
		if(push != null) {
			UsersVO user = userService.selectOne(push.getUserId());
			UserOutVO put_user = new UserOutVO();
			put_user.setEmail(user.getEmail());
			put_user.setStudentName(user.getStudentName());
			put_user.setStudentNumber(user.getStudentNumber());
			put_user.setUnivName(user.getUnivName());
			put_user.setUserId(user.getUserId());
			map.put("user", put_user);
			map.put("favorite", favService.selectAll(user.getUserId()));
			
		}
		else {
			map.put("msg", "현재로그인 되어있지않음");
		}
		return map;
	}
	//회원가입
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> userRegister(@RequestBody UsersVO userVO,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("됬네");
		String tmp = userVO.getPassword();
		
		
		userVO.setPassword(tmp);
		userService.insert(userVO);
		
		
		map.put("msg", "회원가입이 완료되었습니다.");
		return map;
	}	
	
	//회원 가입시 id 중복 체크
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> emailCheck(@RequestBody LoginVO loginVO,
				HttpServletResponse response) {
		System.out.println(loginVO.getEmail());
		System.out.println("이게진짜임");
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(userService.checkId(loginVO.getEmail()) == 1) {
			map.put("msg", "이미 존재하는 E-mail 입니다.");
			
		}
		else {
		map.put("msg", "사용가능한 아이디입니다.");
		}
		
		return map;
	}	
	//로그인 처리
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		OnlinVO push = new OnlinVO();
		pwdEncoder = new BCryptPasswordEncoder();
		if(check != null) {
			if(check.getPassword().equals(loginVO.getPassword())) {
			String ck = pwdEncoder.encode(loginVO.getEmail());
			Cookie cookie = new Cookie("set-cookie", ck);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			push.setKeyId(ck);
			push.setUserId(check.getUserId());
			System.out.println("로그인성공~");
			onlineService.insert(push);
			UserOutVO put_user = new UserOutVO();
			put_user.setEmail(check.getEmail());
			put_user.setStudentName(check.getStudentName());
			put_user.setStudentNumber(check.getStudentNumber());
			put_user.setUnivName(check.getUnivName());
			put_user.setUserId(check.getUserId());
			
			map.put("user", put_user);
			map.put("favorite", favService.selectAll(check.getUserId()));
			map.put("msg", "로그인이 되었습니다.");
			System.out.println("로그인 성공");
			}
			else {
				map.put("msg", "아이디와 비밀번호가 일치하지 않습니다.");
			}
		}
	
		
		return map;
	}	
	
	
	//로그아웃 처리 요청.
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object>  logout(HttpServletRequest request) {
		System.out.println("/user/logout 요청!");
		Map<String, Object> map = new HashMap<String, Object>();
		//쿠키를 받아서 DB에서 제거해준다.

		Cookie[] cookie = request.getCookies();
		
		onlineService.delete(cookie[0].getValue());
		map.put("msg", "로그아웃 되었습니다.");
		return map;
		
	}
}
	
	
	
