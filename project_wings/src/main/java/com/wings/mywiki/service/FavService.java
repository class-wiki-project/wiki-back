package com.wings.mywiki.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.FavVO;

public interface FavService {
	//����
			public void insert(FavVO favVO) throws DataAccessException;
			//��ü��ȸ
			public List<FavVO> selectAll(int userId) throws DataAccessException;
			//����
			public void update(FavVO favVO) throws DataAccessException;
			//����
			public void delete(int favSubjectId) throws DataAccessException;
			//��ȸ2
			public FavVO selectAll2(int favSubjectId) throws DataAccessException;
}