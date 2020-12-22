package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.LoginVO;
import com.wings.mywiki.model.UserOutVO;
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

	private static Logger logger = LoggerFactory.getLogger(UsersController.class);

	// 硫붿씤 �럹�씠吏�
	@RequestMapping(value = "/api/main", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> main(HttpServletResponse response, HttpServletRequest request, HttpSession session)
			throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		session = request.getSession();
		if (session.getAttribute("LOGIN") != null) {
			UsersVO user = (UsersVO) session.getAttribute("LOGIN");
			UserOutVO put_user = new UserOutVO();
			put_user.setEmail(user.getEmail());
			put_user.setStudentName(user.getStudentName());
			put_user.setStudentNumber(user.getStudentNumber());
			put_user.setUnivName(user.getUnivName());
			put_user.setUserId(user.getUserId());
			map.put("user", put_user);
			map.put("favorites", favService.selectAll(user.getUserId()));
		}

		logger.info("�꽭�뀡 �븘�씠�뵒: " + session.getId());
		logger.info("�꽭�뀡 check: " + String.valueOf(session.getAttribute("LOGIN")));

		return map;
	}

	// �쉶�썝媛��엯
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> userRegister(@RequestBody UsersVO userVO, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		String tmp = userVO.getPassword();
		System.out.println(tmp);
		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(tmp);
		System.out.println(pwd);

		userVO.setPassword(pwd);
		userService.insert(userVO);
		map.put("msg", "�쉶�썝媛��엯�씠 �셿猷뚮릺�뿀�뒿�땲�떎.");

		return map;
	}

	// Email Redundancy check
	@RequestMapping(value = "/api/user/emailcheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> emailCheck(@RequestBody LoginVO loginVO, HttpServletResponse response)
			throws IOException {
		System.out.println(loginVO.getEmail());

		Map<String, String> map = new HashMap<String, String>();
		if (userService.checkId(loginVO.getEmail()) == 1) {
			map.put("msg", "以묐났�맂 �씠硫붿씪�엯�땲�떎");
			response.sendError(HttpServletResponse.SC_CONFLICT); // 409 �뿉�윭
		} else {
			map.put("msg", "�궗�슜媛��뒫�븳 �씠硫붿씪�엯�땲�떎");
		}

		return map;
	}

	//
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> Login(@RequestBody LoginVO loginVO, HttpServletResponse response, HttpSession session)
			throws IOException {
		UsersVO check = userService.checkLogin(loginVO.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();

		pwdEncoder = new BCryptPasswordEncoder();
		String pwd = pwdEncoder.encode(loginVO.getPassword());
		System.out.println(pwd);
		if (check != null) {
			if (pwdEncoder.matches(loginVO.getPassword(), check.getPassword())) {
				session.setAttribute("LOGIN", check);
				UserOutVO put_user = new UserOutVO();
				put_user.setEmail(check.getEmail());
				put_user.setStudentName(check.getStudentName());
				put_user.setStudentNumber(check.getStudentNumber());
				put_user.setUnivName(check.getUnivName());
				put_user.setUserId(check.getUserId());

				map.put("users", put_user);
				map.put("favorites", favService.selectAll(check.getUserId()));
			} else {
				map.put("msg", "�븘�씠�뵒�� 鍮꾨�踰덊샇媛� �떎由낅땲�떎.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401�뿉�윭
			}
		}

		logger.info("�꽭�뀡 �븘�씠�뵒: " + session.getId());
		logger.info("�꽭�뀡 check: " + String.valueOf(session.getAttribute("LOGIN")));

		return map;
	}

	// Logout
	@RequestMapping(value = "/api/user/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> logout(HttpSession session) {
		System.out.println("/user/logout accepted!");
		Map<String, String> map = new HashMap<String, String>();
		UsersVO user = (UsersVO) session.getAttribute("LOGIN");

		if (user != null) {
			session.removeAttribute("LOGIN");
			session.invalidate();
			map.put("msg", "濡쒓렇�븘�썐�릺�뿀�뒿�땲�떎.");
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
