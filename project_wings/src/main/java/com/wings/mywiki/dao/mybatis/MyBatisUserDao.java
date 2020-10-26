package com.wings.mywiki.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.mybatis.mapper.UserMapper;

@Repository
public class MyBatisUserDao {
	
	@Autowired
	protected UserMapper userMapper;

}
