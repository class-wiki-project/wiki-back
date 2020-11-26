package com.wings.mywiki.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.UsersVO;

public interface UsersDao {
		//삽입
		public void insert(UsersVO usersVO) throws DataAccessException;
		//전체 조회
		public List<UsersVO> selectAll() throws DataAccessException;
		//한명 조회
		public UsersVO selectOne(int UserId) throws DataAccessException;
		//수정
		public void update(UsersVO usersVO) throws DataAccessException;
		//삭제
		public void delete(int UserId) throws DataAccessException;
		//전체 회원 수 조회
		public int ViewCount() throws DataAccessException;
		//아이디 체크
		public int checkId(String email) throws DataAccessException;
		//로그인 체크
		public UsersVO checkLogin(String email) throws DataAccessException;
}
