package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.CommentDao;
import com.wings.mywiki.model.CommentVO;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao commentDao;

	@Override
	public int inputComment(HashMap<String, Object> map) {
		return commentDao.inputComment(map);
	}

	@Override
	public int updateComment(HashMap<String, Object> map) {
		return commentDao.updateComment(map);
	}

	@Override
	public int deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}

	@Override
	public List<CommentVO> getComments(int boardId) {
		return commentDao.getComments(boardId);
	}

	@Override
	public HashMap<String, Object> getComment(int boardId) {
		return commentDao.getComment(boardId);
	}
} 