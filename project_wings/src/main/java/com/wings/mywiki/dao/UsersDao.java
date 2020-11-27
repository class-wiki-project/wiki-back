package com.wings.mywiki.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UsersVO;

public interface UsersDao {
		//����
		public void insert(UsersVO usersVO) throws DataAccessException;
		//��ü ��ȸ
		public List<UsersVO> selectAll() throws DataAccessException;
		//�Ѹ� ��ȸ
		public UsersVO selectOne(int UserId) throws DataAccessException;
		//����
		public void update(UsersVO usersVO) throws DataAccessException;
		//����
		public void delete(int UserId) throws DataAccessException;
		//��ü ȸ�� �� ��ȸ
		public int ViewCount() throws DataAccessException;
		//���̵� üũ
		public int checkId(String email) throws DataAccessException;
		//�α��� üũ
		public UsersVO checkLogin(String email) throws DataAccessException;
}
