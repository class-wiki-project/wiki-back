package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UserUpdateVO;
import com.wings.mywiki.model.UsersVO;

public interface UsersDao {
		public void insert(UsersVO usersVO) throws DataAccessException;
		public List<UsersVO> selectAll() throws DataAccessException;
		public UsersVO selectOne(int UserId) throws DataAccessException;
		public void update(UserUpdateVO usersVO) throws DataAccessException;
		public void delete(int UserId) throws DataAccessException;
		public int ViewCount() throws DataAccessException;
		public int checkId(String email) throws DataAccessException;
		public UsersVO checkLogin(String email) throws DataAccessException;
		public int report(HashMap<String, Object> map);
		public UsersVO selectWithoutPw(int userId) throws DataAccessException;
}
