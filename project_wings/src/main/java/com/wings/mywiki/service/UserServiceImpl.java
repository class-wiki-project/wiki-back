package com.wings.mywiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl {
	
	@Autowired
	private UserDao userDao;

}
