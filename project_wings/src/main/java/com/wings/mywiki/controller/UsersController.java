package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.wings.mywiki.model.LoginVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.BoardService;
import com.wings.mywiki.service.FavService;
import com.wings.mywiki.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService userService;
	@Autowired
	private FavService favService;
	@Autowired
	private BoardService boardService;

	private BCryptPasswordEncoder pwdEncoder;

	// 메인 페이지
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> main(HttpServletResponse response, HttpServletRequest request, HttpSession session)
			throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		UsersVO user = (UsersVO)WebUtils.getSessionAttribute(request, "loginSession");
		if (user != null) {
			map.put("user", user);
			map.put("favorites", favService.selectAll(user.getUserId()));
		}
		else {
			response.sendError(HttpServletResponse.SC_CONFLICT); // 409 Error
		}
		return map;
	}

	// 회원가입
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> userRegister(@RequestBody UsersVO userVO, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tmp = userVO.getPassword();

		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(tmp);
		System.out.println(pwd);
		userVO.setPassword(pwd);
		userService.insert(userVO);
		map.put("msg", "회원가입이 완료되었습니다.");
		
		return map;
	}

	// 회원 가입시 id 중복 체크
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> emailCheck(@RequestBody LoginVO loginVO, HttpServletResponse response) throws IOException {
		System.out.println(loginVO.getEmail());
		Map<String, String> map = new HashMap<String, String>();

		if (userService.checkId(loginVO.getEmail()) == 1) {
			map.put("msg", "중복된 이메일입니다.");
			response.sendError(HttpServletResponse.SC_CONFLICT); // 409 Error
		} else {
			map.put("msg", "사용가능한 아이디입니다.");
		}

		return map;
	}

	// 로그인 처리
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> Login(@RequestBody LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {
		
		if (session.getAttribute("loginSession") != null) {
			session.removeAttribute("loginSession");
		}
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		pwdEncoder = new BCryptPasswordEncoder();
		if (check != null) {
			if (pwdEncoder.matches(loginVO.getPassword(), check.getPassword())) {
				 
				map.put("user", check);
				map.put("favorites", favService.selectAll(check.getUserId()));
				map.put("msg", "로그인 성공");
				session.setAttribute("loginSession", check);
			} else {
				map.put("msg", "아이디와 비밀번호가 일치하지 않습니다.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401 에러
			}
		}

		return map;
	}

	// 로그아웃 처리 요청.
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		System.out.println("/user/logout 요청!");
		Map<String, String> map = new HashMap<String, String>();
		UsersVO user = (UsersVO) session.getAttribute("loginSession");

		if (user != null) {
			session.removeAttribute("loginSession");
			session.invalidate();
			map.put("msg", "로그아웃 되었습니다.");
		} else {
			map.put("msg", "로그아웃 실패");
			response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 Error
		}

		return map;

	}

	// 신고 하기
	@RequestMapping(value = "/report", method = RequestMethod.PUT, produces = "application/json; charset=utf8")
	public void report(@RequestBody HashMap<String, Object> map) {
		// boardId로 게시글 작성자 찾기
		int reportedUserId = boardService.getUserIdByBoardId((int) map.get("boardId"));
		map.put("reportedUserId", reportedUserId);
		if (userService.report(map) == 0) { // 1:성공, 0:실패
			System.out.println("reporting cannot be done!");
		} else {
			System.out.println("Success!");
		}
	}
}
