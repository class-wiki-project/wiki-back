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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.ReportVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.AdminService;
import com.wings.mywiki.service.BoardService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private BoardService boardService;
	
	// 모든 유저 조회하기
	@GetMapping("getAllUsers")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllUsers( @RequestParam(value="page") int page, @RequestParam(value="amount") int amount, Criteria cri) {
		
		// 파라미터 값으로 criteria 설정
		cri.setAmount(amount); cri.setPage(page);
		//해당페이지 시작 인덱스 설정
		cri.setStartIndex((page-1)*amount);	
		
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		List<UsersVO> userList = adminService.getAllUsers(cri);
		userMap.put("TotalCount", adminService.getUserTotal(cri));
		userMap.put("userList", userList);

		return userMap;
	}
	
	// 모든 유저의 게시물들 조회하기
		@GetMapping("getUsersPost")
		@ResponseStatus(HttpStatus.OK)
		public HashMap<String, Object> getUsersPost(@RequestParam(value="userId") int userId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount, Criteria cri, HttpServletResponse response) {
			
			// 파라미터 값으로 criteria 설정
			cri.setAmount(amount); cri.setPage(page); cri.setUserId(userId);
			//해당페이지 시작 인덱스 설정
			cri.setStartIndex((page-1)*amount);	
			
			HashMap<String, Object> userBoardMap = new HashMap<String, Object>();
			List<BoardVO> userBoardList = adminService.getUsersPost(cri);
			userBoardMap.put("TotalCount", adminService.getTotalCountByUserId(cri));
			userBoardMap.put("boardList", userBoardList);
			
			return userBoardMap;
		}
		
	// 모든 신고 내용 보기
	@GetMapping("getAllReports")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllReports() {
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
	//신고 접수
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int reportUserId = (int) map.get("reportUserId");
		// 신고 된 사람의 신고 된 횟수 +1
		adminService.updateReportedNum(reportUserId);
		
		//신고 된 횟수 가져 옴
		int reportedNum = adminService.getReportedNum(reportUserId);
		if (reportedNum>=5) {	//신고 된 횟수가 5회 이상이면 탈퇴 조취
			adminService.deleteUser(reportUserId);
		}
		
		//신고 리스트에서 삭제
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
	//신고 접수 거부
	@PostMapping("rejectReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> rejectReport(@RequestBody HashMap<String, Object> map) {
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
}
