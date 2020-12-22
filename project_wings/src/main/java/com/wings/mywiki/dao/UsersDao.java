package com.wings.mywiki.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UsersVO;

public interface UsersDao {
		public void insert(UsersVO usersVO) throws DataAccessException;
		public List<UsersVO> selectAll() throws DataAccessException;
		public UsersVO selectOne(int UserId) throws DataAccessException;
		public void update(UsersVO usersVO) throws DataAccessException;
		public void delete(int UserId) throws DataAccessException;
		public int ViewCount() throws DataAccessException;
		public int checkId(String email) throws DataAccessException;
		public UsersVO checkLogin(String email) throws DataAccessException;
}
