package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wings.mywiki.service.CommentService;

@Controller
@RequestMapping("/board")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/inputComment", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody HashMap<String, Object> inputComment(@RequestBody HashMap<String, Object> map) {
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();

		while (entries.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) entries.next();
			System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());
		}

		if (commentService.inputComment(map) == 0) { // 성공:1, 실패:0
			System.out.println("Updating product cannot be done!");
		} else {
			System.out.println("Success!!!");
		}

		return map;
	}
}
