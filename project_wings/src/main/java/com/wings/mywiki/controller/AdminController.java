package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wings.mywiki.model.CommentVO;
import com.wings.mywiki.model.ReportVO;
import com.wings.mywiki.service.AdminService;
import com.wings.mywiki.service.BoardService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BoardService boardService;
	
	//모든 신고 내용 보기
	 @GetMapping("getAllReports")
	 @ResponseStatus(HttpStatus.OK)
	 public HashMap<String, Object> getAllReports() {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      List<ReportVO> reportList= adminService.getAllReports();
	      map.put("reportList", reportList);

	      return map;
	   }
	 
	 // 공지사항 등록
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insertNotice(@RequestBody HashMap<String, Object> map, HttpServletResponse response) throws Exception {
		if (adminService.createNotice(map) == 1) { // 성공 1, 실패 0
			System.out.println(map.get("userId") + "의 공지사항 created.");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		HashMap<String, Object> insert = new HashMap<String, Object>();
		insert.put("Notice", boardService.getBoard((int)map.get("boardId")));

		return insert;
	}
	
	// 모든 유저 조회
	@GetMapping("getAllUsers")
	 @ResponseStatus(HttpStatus.OK)
	 public HashMap<String, Object> getAllUsers() {
		HashMap<String, Object> map = new HashMap<String, Object>();

	      return map;
	   }
}
