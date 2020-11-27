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
import com.wings.mywiki.model.WikiVO;
import com.wings.mywiki.service.WikiService;

@Controller
@RequestMapping("/wiki")
public class WikiController {
	@Autowired
	private WikiService WikiService;

	//wiki ������ ���� ��
	@RequestMapping(value = "/showWiki", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> showWiki(@RequestParam int subjectId) {
		WikiVO wikiVO = WikiService.getWiki(subjectId);
		List<ClassificationVO> classificationList = WikiService.getClassification(wikiVO.getWikiId());

		// Json���� �ѱ��
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wikiVO", wikiVO);
		map.put("classificationList", classificationList);

		return map;
	}

	// wiki ����
	@RequestMapping(value = "/editWiki", method = RequestMethod.PUT, produces = "application/json; charset=utf8") //***PUT���� ����!
	public @ResponseBody HashMap<String, String> editWiki(@RequestBody HashMap<String, String> map) throws JsonParseException, JsonMappingException, IOException { //{key(id),value(text)}
		if (WikiService.editWiki(map) == 0) { // ����:1, ����:0
			System.out.println("Updating wiki cannot be done!");
		}else {
			System.out.println("Success!!!");
		}

		//����Ʈ���� wiki, classification ���� �ٽ� �Ѱ���� ��
		return map;
	}
}