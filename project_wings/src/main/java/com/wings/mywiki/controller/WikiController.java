package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.WikiVO;
import com.wings.mywiki.service.WikiService;

@RestController
@RequestMapping("/wiki")
public class WikiController {
	@Autowired
	private WikiService wikiService;

	// wiki 내용보기
	@GetMapping(value = "/showWiki")
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
	@PutMapping(value = "/editWiki")
	public HashMap<String, Object> editWiki(@RequestBody HashMap<String, String> map) {
		if (wikiService.editWiki(map) == 0) { // �꽦怨�:1, �떎�뙣:0
			System.out.println("Updating wiki cannot be done!");
		} else {
			System.out.println("Success!!!");
		}

		HashMap<String, Object> wikiMap = new HashMap<String, Object>();
		int wikiId = Integer.parseInt(map.get("wikiId"));
		WikiVO wikiVO = wikiService.getWikiByWikiId(wikiId);
		List<ClassificationVO> classificationList = wikiService.getClassification(wikiId);
		wikiMap.put("wikiVO", wikiVO);
		wikiMap.put("classificationList", classificationList);

		return wikiMap;
	}
	
	// check classification
	@PostMapping(value = "/checkClassification")
	public HashMap<String, Object> checkClassification(@RequestBody HashMap<String, Object> map) {
		HashMap<String, Object> wikiMap = new HashMap<String,Object>();
		String groupId = (String) map.get("groupId");
		int wikiId = (int) map.get("wikiId");
		
		int isAble = isAbleMakeToGroup(groupId,wikiId);	//생성 가능한 적합한 목록인지 확인
		wikiMap.put("isAble", isAble);
		
		return wikiMap;
	}

	// classification is added into wiki
	@PostMapping(value = "/addClassification")
	public HashMap<String, Object> addWiki(@RequestBody HashMap<String, Object> map) {
		HashMap<String, Object> wikiMap = new HashMap<String,Object>();
		String groupId = (String) map.get("groupId");
		
		if (wikiService.addClassification(map) == 0) { 
			System.out.println("Fail to add classification");
		} else {
			System.out.println("Success!!!");
		}
		
		List<ClassificationVO> classificationList = wikiService.getClassification((int)map.get("wikiId"));
		wikiMap.put("classificationList", classificationList);
			
		return wikiMap;
	}
	
	//생성 가능한 적합한 목록인지 확인
	public int isAbleMakeToGroup(String groupId,int wikiId) {
		String[] groupIdArray = groupId.split("\\.");
		List<String> groupList = wikiService.getAllGroupId(wikiId);
		int isAble=0;
		int len = groupIdArray.length;
		
		for(String g : groupList) {   //만약 이미 있는 그룹이라면 생성 불가
	        if(g.equals(groupId))
	           return 0;
	     }
		if(len>4)	// a.b.c.d형태 까지만 가능
			return 0;
		
		if(groupList.size()==0) {	//아예 처음 목록 생성
			if(groupId.equals("1") || groupId.equals("2"))
				isAble=1;	//가능
		}
		
		for(String g : groupList) {	//원래 있던 groupId+.1은 생성 가능
			if((g+".1").equals(groupId)) {
				isAble=1;	//가능
				break;
			}
		}
		
		for(String g : groupList) {	
			String[] gList=g.split("\\.");
			if(len==gList.length) {
				if((Integer.parseInt(gList[gList.length-1])+1)==Integer.parseInt(groupIdArray[groupIdArray.length-1])) {
					isAble=1;
					break;
				}
			}
		}
		
		return isAble;
	}
}