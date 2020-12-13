package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.UserOutVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.SubjectService;

@Controller
public class SubjectController {
	@Autowired
	private SubjectService subService;
	
	//1) ��ü ���� ��ȸ
	@RequestMapping(value = "/api/subject/select", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> select() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SubjectVO> list = subService.selectAll();
		map.put("subjects", list);
		
		
		return map;
	}
	
	// 2) 과목 정보 수정하기
	@RequestMapping(value = "/api/subject/update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> update(SubjectVO subjectVO) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		subService.update(subjectVO);
		map.put("msg", "수정이 완료되었습니다.");
		
		
		return map;
	}
	// 3) 과목 추가하기
	@RequestMapping(value = "/api/subject/insert", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> insert(SubjectVO subjectVO) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		subService.insert(subjectVO);
		map.put("msg", "추가되었습니다.");
		
		return map;
	}
	// 4) 과목 삭제하기
	@RequestMapping(value = "/api/subject/delete", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Map<String, Object> delete(int subjectId) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		subService.delete(subjectId);
		map.put("msg", "삭제가 완료되었습니다");
		
		
		return map;
	}
}