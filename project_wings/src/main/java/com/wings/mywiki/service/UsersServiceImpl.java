package com.wings.mywiki.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.UsersDao;
import com.wings.mywiki.model.UsersVO;
@Service
@Transactional
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao usersDao;
	@Override
	public void insert(UsersVO usersVO) throws DataAccessException {
		usersDao.insert(usersVO);
	}

	@Override
	public List<UsersVO> selectAll() throws DataAccessException {
		return usersDao.selectAll();
	}

	@Override
	public UsersVO selectOne(int UserId) throws DataAccessException {
		// TODO Auto-generated method stub
		return usersDao.selectOne(UserId);
	}

	@Override
	public void update(UsersVO usersVO) throws DataAccessException {
		// TODO Auto-generated method stub
		usersDao.update(usersVO);
	}

	@Override
	public void delete(int UserId) throws DataAccessException {
		usersDao.delete(UserId);
	}

	@Override
	public int ViewCount() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkId(String email) throws DataAccessException {
		// TODO Auto-generated method stub
		return usersDao.checkId(email);
	}

	@Override
	public UsersVO checkLogin(String email) throws DataAccessException {
		// TODO Auto-generated method stub
		return usersDao.checkLogin(email);
	}

}
