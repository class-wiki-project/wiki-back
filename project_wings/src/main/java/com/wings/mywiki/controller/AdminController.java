package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wings.mywiki.model.ReportVO;
import com.wings.mywiki.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	//모든 신고 내용 보기
	 @RequestMapping(value = "/getAllReports", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	 @ResponseBody
	 public HashMap<String, Object> getComments() {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      List<ReportVO> reportList= adminService.getAllReports();
	      map.put("reportList", reportList);

	      return map;
	   }
}
