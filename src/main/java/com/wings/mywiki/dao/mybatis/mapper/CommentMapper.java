package com.wings.mywiki.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import com.wings.mywiki.model.CommentVO;

public interface CommentMapper {
	public int inputComment(HashMap<String, Object> map);
	public int updateComment(HashMap<String, Object> map);
	public int deleteComment(int commentId);
	public List<CommentVO> getComments(int boardId);
}
