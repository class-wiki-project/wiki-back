package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wings.mywiki.model.CommentVO;

@Service
public interface CommentService {
	public int inputComment(HashMap<String, Object> map);
	public int updateComment(HashMap<String, Object> map);
	public int deleteComment(int commentId);
	public List<CommentVO> getComments(int boardId);
}