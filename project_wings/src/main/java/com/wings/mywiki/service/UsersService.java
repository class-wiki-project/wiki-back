package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UserUpdateVO;
import com.wings.mywiki.model.UsersVO;

public interface UsersService {
		//����
		void insert(UsersVO usersVO) throws DataAccessException;
		//��ü ��ȸ
		List<UsersVO> selectAll() throws DataAccessException;
		//�Ѹ� ��ȸ
		UsersVO selectOne(int UserId) throws DataAccessException;
		//����
		void update(UserUpdateVO usersVO) throws DataAccessException;
		//����
		void delete(int UserId) throws DataAccessException;
		//��ü ȸ�� �� ��ȸ
		int ViewCount() throws DataAccessException;
		//���̵� üũ
		int checkId(String email) throws DataAccessException;
		//�α��� üũ
		UsersVO checkLogin(String email) throws DataAccessException;
		int report(HashMap<String, Object> map);
		UsersVO selectWithoutPw(int userId) throws DataAccessException;
}
