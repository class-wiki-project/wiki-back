package com.wings.mywiki.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.CommentDao;

@Service
@Transactional
public class CommentServiceImple implements CommentService{
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public int inputComment(HashMap<String, Object> map) {
		return commentDao.inputComment(map);
	}
}
