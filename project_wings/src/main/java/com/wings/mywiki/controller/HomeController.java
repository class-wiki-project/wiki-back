package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.service.HomeService;

@Controller
public class HomeController {
	@Autowired
	private HomeService homeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	// 메인화면에서 과목명 검색
	@RequestMapping(value = "/searchSubject", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> searchSubject(@RequestParam String searchName){
		List<SubjectVO> subjectList = homeService.getAllSubjectByName(searchName);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String emp = "검색 결과가 없습니다";
		
		if(subjectList.isEmpty()) {	//결과가 없다면
			map.put("empty", emp);
		}else {
			map.put("subjectList", subjectList);
		}
		System.out.println(map);
		return map;
	}
}