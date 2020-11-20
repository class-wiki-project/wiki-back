package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.CommentDao;
import com.wings.mywiki.dao.mybatis.mapper.CommentMapper;

@Repository
public class MybatisCommentDao implements CommentDao{
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public int inputComment(HashMap<String, Object> map) {
		return commentMapper.inputComment(map);
	}
}
