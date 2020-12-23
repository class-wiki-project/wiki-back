package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	// 모든 신고 내용 보기
	@GetMapping("getAllReports")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllReports() {
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
	//신고 승인 & 거부
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int approve = (int)map.get("approve");	//1:승인, 0:거절
		
		if(approve==1) {
			int reportedUserId = adminService.getReportedUserId((int)map.get("reportId"));
			
			// 신고 된 사람의 신고 된 횟수 +1
			adminService.updateReportedNum(reportedUserId);
			
			//신고 된 횟수 가져 옴
			int reportedNum = adminService.getReportedNum(reportedUserId);
			if (reportedNum>=5) {	//신고 된 횟수가 5회 이상이면 탈퇴 조취
				adminService.deleteUser(reportedUserId);
			}
		}
		
		//신고 리스트에서 삭제
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}

	// 공지사항 등록
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insertNotice(@RequestBody HashMap<String, Object> map, HttpServletResponse response)
			throws Exception {
		if (adminService.createNotice(map) == 1) { // 성공 1, 실패 0
			System.out.println(map.get("userId") + "의 공지사항 created.");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		HashMap<String, Object> insert = new HashMap<String, Object>();
		insert.put("Notice", boardService.getBoard((int) map.get("boardId")));

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
