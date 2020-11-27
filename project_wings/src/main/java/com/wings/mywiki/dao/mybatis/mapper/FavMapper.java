package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.FavVO;

public interface FavMapper {
	//����
	void insert(FavVO favVO) throws DataAccessException;
	//��ü��ȸ
	List<FavVO> selectAll(int userId) throws DataAccessException;
	//����
	void update(FavVO favVO) throws DataAccessException;
	//����
	void delete(int favSubjectId) throws DataAccessException;
	//��ü��ȸ2
	FavVO selectAll2(int favSubjectId) throws DataAccessException;
}
