package com.wings.mywiki.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.FavVO;

public interface FavService {
	//삽입
			public void insert(FavVO favVO) throws DataAccessException;
			//전체조회
			public List<FavVO> selectAll(int userId) throws DataAccessException;
			//수정
			public void update(FavVO favVO) throws DataAccessException;
			//삭제
			public void delete(int favSubjectId) throws DataAccessException;
			//조회2
			public FavVO selectAll2(int favSubjectId) throws DataAccessException;
}