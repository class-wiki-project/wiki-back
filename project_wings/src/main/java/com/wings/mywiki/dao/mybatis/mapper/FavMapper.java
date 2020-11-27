package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.FavVO;

public interface FavMapper {
	//삽입
	void insert(FavVO favVO) throws DataAccessException;
	//전체조회
	List<FavVO> selectAll(int userId) throws DataAccessException;
	//수정
	void update(FavVO favVO) throws DataAccessException;
	//삭제
	void delete(int favSubjectId) throws DataAccessException;
	//전체조회2
	FavVO selectAll2(int favSubjectId) throws DataAccessException;
}
