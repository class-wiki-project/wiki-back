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

<<<<<<< HEAD
	// 모든 신고 내용 보기
=======
	// 紐⑤뱺 �떊怨� �궡�슜 蹂닿린
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
	@GetMapping("getAllReports")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllReports() {
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
<<<<<<< HEAD
	//신고 승인 & 거부
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int approve=(int) map.get("approve");	//1:승인, 0:거절
		
		if (approve==1) {
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
=======
	//�떊怨� �젒�닔
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int reportUserId = (int) map.get("reportUserId");
		// �떊怨� �맂 �궗�엺�쓽 �떊怨� �맂 �슏�닔 +1
		adminService.updateReportedNum(reportUserId);
		
		//�떊怨� �맂 �슏�닔 媛��졇 �샂
		int reportedNum = adminService.getReportedNum(reportUserId);
		if (reportedNum>=5) {	//�떊怨� �맂 �슏�닔媛� 5�쉶 �씠�긽�씠硫� �깉�눜 議곗랬
			adminService.deleteUser(reportUserId);
		}
		
		//�떊怨� 由ъ뒪�듃�뿉�꽌 �궘�젣
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
<<<<<<< HEAD
	// 공지사항 등록
=======
	//�떊怨� �젒�닔 嫄곕��
	@PostMapping("rejectReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> rejectReport(@RequestBody HashMap<String, Object> map) {
		//�떊怨� 由ъ뒪�듃�뿉�꽌 �궘�젣
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}

	// 怨듭���궗�빆 �벑濡�
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insertNotice(@RequestBody HashMap<String, Object> map, HttpServletResponse response)
			throws Exception {
<<<<<<< HEAD
		if (adminService.createNotice(map) == 1) { // 성공 1, 실패 0
			System.out.println(map.get("userId") + "의 공지사항 created.");
=======
		if (adminService.createNotice(map) == 1) { // �꽦怨� 1, �떎�뙣 0
			System.out.println(map.get("userId") + "�쓽 怨듭���궗�빆 created.");
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		HashMap<String, Object> insert = new HashMap<String, Object>();
		insert.put("Notice", boardService.getBoard((int) map.get("boardId")));

		return insert;
	}

<<<<<<< HEAD
	// 모든 유저 조회
=======
	// 紐⑤뱺 �쑀��� 議고쉶
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
	@GetMapping("getAllUsers")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllUsers() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		return map;
	}
}
