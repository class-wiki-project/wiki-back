package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	//wiki 내용보기
	@RequestMapping(value = "/showWiki", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> showWiki(@RequestParam int subjectId) {
		WikiVO wikiVO = wikiService.getWiki(subjectId);
		List<ClassificationVO> classificationList = wikiService.getClassification(wikiVO.getWikiId());
		SubjectVO subjectVO = wikiService.getSubject(subjectId);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wikiVO", wikiVO);
		map.put("classificationList", classificationList);
		map.put("subjectVO", subjectVO);

		return map;
	}

	// wiki 수정
	@RequestMapping(value = "/editWiki", method = RequestMethod.PUT, produces = "application/json; charset=utf8") //***PUT占쏙옙占쏙옙 占쏙옙占쏙옙!
	public @ResponseBody HashMap<String,Object> editWiki(@RequestBody HashMap<String, String> map){
		if (wikiService.editWiki(map) == 0) { // �꽦怨�:1, �떎�뙣:0
			System.out.println("Updating wiki cannot be done!");
		}else {
			System.out.println("Success!!!");
		}
		
		HashMap<String, Object> wikiMap = new HashMap<String, Object>();
		int wikiId = Integer.parseInt(map.get("wikiId"));
		WikiVO wikiVO = wikiService.getWikiByWikiId(wikiId);
		List<ClassificationVO> classificationList = wikiService.getClassification(wikiId);
		wikiMap.put("wikiVO", wikiVO);
		wikiMap.put("classificationList", classificationList);
		
		
		/*if(flag==true) {
			System.out.println("fail!!!");
			HashMap<String, String> failMap = new HashMap<>();
			failMap.put("fail", "already in use");
			
			return failMap;
		}else {
			flag=true;
			
			if (WikiService.editWiki(map) == 0) { �꽦怨�:1, �떎�뙣:0
				System.out.println("Updating wiki cannot be done!");
			}else {
				System.out.println("Success!!!");
			}
			flag=false;
			
			return map;
			}
			*/
			return wikiMap;
	}
}