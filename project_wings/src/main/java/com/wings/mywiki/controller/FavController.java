package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
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
import com.wings.mywiki.model.UsersVO;
import com.wings.mywiki.service.FavService;

@Controller
public class FavController {
	@Autowired
	private FavService favService;
	
	//즐겨찾기 추가하기
	@RequestMapping(value = "/api/fav/insert", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody FavVO favVO,
										HttpServletResponse response
										) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("받은 즐찾과목id: " + favVO.getFavSubjectId());
		favService.insert(favVO);
		map.put("favorite", favService.selectAll(favVO.getUserId()));
		map.put("msg", "즐겨찾기에 추가되었습니다.");
		return map;
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