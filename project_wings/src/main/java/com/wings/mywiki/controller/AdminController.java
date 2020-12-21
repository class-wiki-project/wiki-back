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

	// ��� �Ű� ���� ����
	@GetMapping("getAllReports")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllReports() {
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
	//�Ű� ����
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int reportUserId = (int) map.get("reportUserId");
		// �Ű� �� ����� �Ű� �� Ƚ�� +1
		adminService.updateReportedNum(reportUserId);
		
		//�Ű� �� Ƚ�� ���� ��
		int reportedNum = adminService.getReportedNum(reportUserId);
		if (reportedNum>=5) {	//�Ű� �� Ƚ���� 5ȸ �̻��̸� Ż�� ����
			adminService.deleteUser(reportUserId);
		}
		
		//�Ű� ����Ʈ���� ����
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
	//�Ű� ���� �ź�
	@PostMapping("rejectReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> rejectReport(@RequestBody HashMap<String, Object> map) {
		//�Ű� ����Ʈ���� ����
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}

	// �������� ���
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insertNotice(@RequestBody HashMap<String, Object> map, HttpServletResponse response)
			throws Exception {
		if (adminService.createNotice(map) == 1) { // ���� 1, ���� 0
			System.out.println(map.get("userId") + "�� �������� created.");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		HashMap<String, Object> insert = new HashMap<String, Object>();
		insert.put("Notice", boardService.getBoard((int) map.get("boardId")));

		return insert;
	}

	// ��� ���� ��ȸ
	@GetMapping("getAllUsers")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllUsers() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		return map;
	}
}
