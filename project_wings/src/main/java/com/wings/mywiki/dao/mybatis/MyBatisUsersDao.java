package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.UsersDao;
import com.wings.mywiki.dao.mybatis.mapper.UsersMapper;
import com.wings.mywiki.model.UsersVO;

@Repository
public class MyBatisUsersDao implements UsersDao{
	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public void insert(UsersVO usersVO) throws DataAccessException {
		usersMapper.insert(usersVO);
		
	}

	@Override
	public List<UsersVO> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return usersMapper.selectAll();
	}

	@Override
	public UsersVO selectOne(int UserId) throws DataAccessException {
		// TODO Auto-generated method stub
		return usersMapper.selectOne(UserId);
	}

	@Override
	public void update(UsersVO usersVO) throws DataAccessException {
		usersMapper.update(usersVO);
		
	}

	@Override
	public void delete(int UserId) throws DataAccessException {
		usersMapper.delete(UserId);
		
	}

	@Override
	public int ViewCount() throws DataAccessException {
		return usersMapper.ViewCount();
	}

	@Override
	public int checkId(String email) throws DataAccessException {
		
		return usersMapper.checkId(email);
	}

	@Override
	public UsersVO checkLogin(String email) throws DataAccessException {
		// TODO Auto-generated method stub
		return usersMapper.checkLogin(email);
	}

	@Override
	public int report(HashMap<String, Object> map) {
		return usersMapper.report(map);
	}

}
