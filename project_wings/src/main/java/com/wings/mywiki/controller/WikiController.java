package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.WikiVO;
import com.wings.mywiki.service.WikiService;

@Controller
@RequestMapping("/wiki")
public class WikiController {
	@Autowired
	private WikiService wikiService;
	//private boolean flag=false;

	//wiki 페이지 보여 줌
	@RequestMapping(value = "/showWiki", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> showWiki(@RequestParam int subjectId) {
		WikiVO wikiVO = wikiService.getWiki(subjectId);
		List<ClassificationVO> classificationList = wikiService.getClassification(wikiVO.getWikiId());
		SubjectVO subjectVO = wikiService.getSubject(subjectId);

		// Json으로 넘기기
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wikiVO", wikiVO);
		map.put("classificationList", classificationList);
		map.put("subjectVO", subjectVO);

		return map;
	}

	// wiki 수정
	@RequestMapping(value = "/editWiki", method = RequestMethod.PUT, produces = "application/json; charset=utf8") //***PUT으로 수정!
	public @ResponseBody HashMap<String, String> editWiki(@RequestBody HashMap<String, String> map) throws JsonParseException, JsonMappingException, IOException { //{key(id),value(text)}
		/*synchronized (map) {
			try {
				if (WikiService.editWiki(map) == 0) { // 성공:1, 실패:0
					System.out.println("Updating wiki cannot be done!");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		if (wikiService.editWiki(map) == 0) { // 성공:1, 실패:0
			System.out.println("Updating wiki cannot be done!");
		}else {
			System.out.println("Success!!!");
		}
		
		/*if(flag==true) {
			System.out.println("fail!!!");
			HashMap<String, String> failMap = new HashMap<>();
			failMap.put("fail", "already in use");
			
			return failMap;
		}else {
			flag=true;
			
			if (WikiService.editWiki(map) == 0) { // 성공:1, 실패:0
				System.out.println("Updating wiki cannot be done!");
			}else {
				System.out.println("Success!!!");
			}
			flag=false;
			
			return map;
			}
			*/
			return map;
	}
}