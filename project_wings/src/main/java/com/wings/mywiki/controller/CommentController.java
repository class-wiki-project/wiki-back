package com.wings.mywiki.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wings.mywiki.model.CommentVO;
import com.wings.mywiki.service.CommentService;

@Controller
@RequestMapping("/board")
public class CommentController {
	@Autowired
	private CommentService commentService;

	// ��� �Է�
	@RequestMapping(value = "/inputComment", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public @ResponseBody HashMap<String, Object> inputComment(@RequestBody HashMap<String, Object> map) {
		if (commentService.inputComment(map) == 0) { // ����:1, ����:0
			System.out.println("inputing comment cannot be done!");
		} else {
			System.out.println("Success!!!");
		}

		return map;
	}

	// ��� ����
	@RequestMapping(value = "/updateComment", method = RequestMethod.PUT, produces = "application/json; charset=utf8")
	public @ResponseBody HashMap<String, Object> updateComment(@RequestBody HashMap<String, Object> map) {
		if (commentService.updateComment(map) == 0) { // ����:1, ����:0
			System.out.println("Updating comment cannot be done!");
		} else {
			System.out.println("Success!!!");
		}

		return map;
	}

	// ��� ����
	@RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE, produces = "application/json; charset=utf8")
	public @ResponseBody List<CommentVO> deleteComment(@RequestBody HashMap<String, Object> map) {
		int commentId = (int) map.get("commentId");
		int boardId = (int) map.get("boardId");

		if (commentService.deleteComment(commentId) == 0) { // ����:1, ����:0
			System.out.println("deleting comment cannot be done!");
		} else {
			System.out.println("Success!!!");
		}

		List<CommentVO> commentList = commentService.getComments(boardId);

		//���� �� ���� �� comments�� ��ȯ
		return commentList;
	}
}