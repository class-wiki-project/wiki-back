package com.wings.mywiki.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.FavVO;

public interface FavDao {
		public void insert(FavVO favVO) throws DataAccessException;
		public List<FavVO> selectAll(int userId) throws DataAccessException;
		public void update(FavVO favVO) throws DataAccessException;
		public void delete(int favSubjectId) throws DataAccessException;
		public FavVO selectAll2(int favSubjectId) throws DataAccessException;
}
