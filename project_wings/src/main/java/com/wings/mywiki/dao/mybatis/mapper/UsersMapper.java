package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UsersVO;

public interface UsersMapper {
	//삽입
	void insert(UsersVO usersVO) throws DataAccessException;
	//전체 조회
	List<UsersVO> selectAll() throws DataAccessException;
	//한명 조회
	UsersVO selectOne(int UserId) throws DataAccessException;
	//수정
	void update(UsersVO usersVO) throws DataAccessException;
	//삭제
	void delete(int UserId) throws DataAccessException;
	//전체 회원 수 조회
	int ViewCount() throws DataAccessException;
	//아이디 체크
	int checkId(String email) throws DataAccessException;
	//로그인 체크
	UsersVO checkLogin(String email) throws DataAccessException;
}