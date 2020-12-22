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
	// ∏µÁ Ω≈∞Ì ≥ªøÎ ∫∏±‚
=======
	// Î™®Îì† Ïã†Í≥† ÎÇ¥Ïö© Î≥¥Í∏∞
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
	//Ω≈∞Ì Ω¬¿Œ & ∞≈∫Œ
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int approve=(int) map.get("approve");	//1:Ω¬¿Œ, 0:∞≈¿˝
		
		if (approve==1) {
			int reportedUserId = adminService.getReportedUserId((int)map.get("reportId"));
			
			// Ω≈∞Ì µ» ªÁ∂˜¿« Ω≈∞Ì µ» »Ωºˆ +1
			adminService.updateReportedNum(reportedUserId);
			
			//Ω≈∞Ì µ» »Ωºˆ ∞°¡Æ ø»
			int reportedNum = adminService.getReportedNum(reportedUserId);
			if (reportedNum>=5) {	//Ω≈∞Ì µ» »Ωºˆ∞° 5»∏ ¿ÃªÛ¿Ã∏È ≈ª≈ ¡∂√Î
				adminService.deleteUser(reportedUserId);
			}
		}
		//Ω≈∞Ì ∏ÆΩ∫∆Æø°º≠ ªË¡¶
=======
	//Ïã†Í≥† Ï†ëÏàò
	@PostMapping("approveReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> approveReport(@RequestBody HashMap<String, Object> map) {
		int reportUserId = (int) map.get("reportUserId");
		// Ïã†Í≥† Îêú ÏÇ¨ÎûåÏùò Ïã†Í≥† Îêú ÌöüÏàò +1
		adminService.updateReportedNum(reportUserId);
		
		//Ïã†Í≥† Îêú ÌöüÏàò Í∞ÄÏ†∏ Ïò¥
		int reportedNum = adminService.getReportedNum(reportUserId);
		if (reportedNum>=5) {	//Ïã†Í≥† Îêú ÌöüÏàòÍ∞Ä 5Ìöå Ïù¥ÏÉÅÏù¥Î©¥ ÌÉàÌá¥ Ï°∞Ï∑®
			adminService.deleteUser(reportUserId);
		}
		
		//Ïã†Í≥† Î¶¨Ïä§Ìä∏ÏóêÏÑú ÏÇ≠Ï†ú
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}
	
<<<<<<< HEAD
	// ∞¯¡ˆªÁ«◊ µÓ∑œ
=======
	//Ïã†Í≥† Ï†ëÏàò Í±∞Î∂Ä
	@PostMapping("rejectReport")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> rejectReport(@RequestBody HashMap<String, Object> map) {
		//Ïã†Í≥† Î¶¨Ïä§Ìä∏ÏóêÏÑú ÏÇ≠Ï†ú
		adminService.deleteReport((int) map.get("reportId"));
		
		HashMap<String, Object> reportMap = new HashMap<String, Object>();
		List<ReportVO> reportList = adminService.getAllReports();
		reportMap.put("reportList", reportList);

		return reportMap;
	}

	// Í≥µÏßÄÏÇ¨Ìï≠ Îì±Î°ù
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insertNotice(@RequestBody HashMap<String, Object> map, HttpServletResponse response)
			throws Exception {
<<<<<<< HEAD
		if (adminService.createNotice(map) == 1) { // º∫∞¯ 1, Ω«∆– 0
			System.out.println(map.get("userId") + "¿« ∞¯¡ˆªÁ«◊ created.");
=======
		if (adminService.createNotice(map) == 1) { // ÏÑ±Í≥µ 1, Ïã§Ìå® 0
			System.out.println(map.get("userId") + "Ïùò Í≥µÏßÄÏÇ¨Ìï≠ created.");
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		HashMap<String, Object> insert = new HashMap<String, Object>();
		insert.put("Notice", boardService.getBoard((int) map.get("boardId")));

		return insert;
	}

<<<<<<< HEAD
	// ∏µÁ ¿Ø¿˙ ¡∂»∏
=======
	// Î™®Îì† Ïú†Ï†Ä Ï°∞Ìöå
>>>>>>> 2dd8b3379e7909800f7c5810f7de0e2186988c9d
	@GetMapping("getAllUsers")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> getAllUsers() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		return map;
	}
}
