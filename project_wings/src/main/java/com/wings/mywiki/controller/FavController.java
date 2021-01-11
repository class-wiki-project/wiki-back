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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wings.mywiki.model.FavVO;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.FavService;
import com.wings.mywiki.service.SubjectService;
import com.wings.mywiki.service.UsersService;

@Controller
public class FavController {
	@Autowired
	private FavService favService;
	@Autowired
	private UsersService userService; 
	@Autowired
	private SubjectService subService;
	//즐겨찾기 추가하기
	@RequestMapping(value = "/api/fav/insert", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody HashMap<String, Object> map,
										HttpServletResponse response
										) throws IOException {
		Map<String, Object> push = new HashMap<String, Object>();
		//유저id로 
		System.out.println("즐겨찾기 삽입 호출");
		UsersVO user = userService.selectOne((int)map.get("userId"));
		SubjectVO subject = subService.selectOne((int)map.get("subjectId"));
		List<FavVO> check = favService.selectAll((int)map.get("userId"));
		
		for(int i=0; i<check.size(); i++) {
			if(check.get(i).getSubjectId() == (int)map.get("subjectId")) {
				response.sendError(HttpServletResponse.SC_CONFLICT);
				push.put("msg", "중복된 과목입니다.");
				return push;
			}
		}
		if(user != null) {
		FavVO result = new FavVO();
		result.setUserId((int)map.get("userId"));
		result.setSubjectId((int)map.get("subjectId"));
		result.setSubjectName(subject.getSubjectName());
		result.setProfessor(subject.getProfessor());
		result.setIconName((String)map.get("iconName"));
		favService.insert(result);
		push.put("favorites", favService.selectAll((int)map.get("userId")));
		push.put("msg", "즐겨찾기 등록이 완료되었습니다.");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			push.put("msg", "등록 실패");
		}
		return push;
	}
	
	//즐겨찾기 수정하기
	@RequestMapping(value = "/api/fav/update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> update(@RequestBody FavVO favVO,
										HttpServletResponse response
										) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		favService.update(favVO);
		map.put("favorite", favService.selectAll(favVO.getUserId()));
		map.put("msg", "즐겨찾기가 수정되었습니다.");
		return map;
	}
	
	//즐겨찾기 삭제하기
	@RequestMapping(value = "/api/fav/delete", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="favSubjectId") int favSubjectId,
										HttpServletResponse response
										) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("favSubjectId: " + favSubjectId);
		favService.delete(favSubjectId);
		FavVO temp = favService.selectAll2(favSubjectId);
		if(temp != null) {
		map.put("favorite", favService.selectAll(temp.getUserId()));
		map.put("msg", "즐겨찾기가 삭제되었습니다.");
		}
		return map;
	}
	
}