package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.CommentDao;
import com.wings.mywiki.dao.mybatis.mapper.CommentMapper;
import com.wings.mywiki.model.CommentVO;

@Repository
public class MybatisCommentDao implements CommentDao{
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int inputComment(HashMap<String, Object> map) {
		return commentMapper.inputComment(map);
	}

	@Override
	public int updateComment(HashMap<String, Object> map) {
		return commentMapper.updateComment(map);
	}

	@Override
	public int deleteComment(int commentId) {
		return commentMapper.deleteComment(commentId);
	}

	@Override
	public List<CommentVO> getComments(int boardId) {
		return commentMapper.getComments(boardId);
	}

	@Override
	public HashMap<String, Object> getComment(int boardId) {
		return commentMapper.getComment(boardId);
	}
}