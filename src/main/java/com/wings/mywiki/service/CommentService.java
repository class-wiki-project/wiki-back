package com.wings.mywiki.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
	public int inputComment(HashMap<String, Object> map);
}
