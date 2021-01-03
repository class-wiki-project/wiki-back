package com.wings.mywiki.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UsersVO;

public interface UsersMapper {
	//����
	void insert(UsersVO usersVO) throws DataAccessException;
	//��ü ��ȸ
	List<UsersVO> selectAll() throws DataAccessException;
	//�Ѹ� ��ȸ
	UsersVO selectOne(int UserId) throws DataAccessException;
	//����
	void update(UsersVO usersVO) throws DataAccessException;
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