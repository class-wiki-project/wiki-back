package com.wings.mywiki.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wings.mywiki.model.SubjectVO;

@Service
public interface SubjectService {

	public void insert(SubjectVO subjectVO) throws DataAccessException;
	public List<SubjectVO> selectAll() throws DataAccessException;
	public void update(SubjectVO subjectVO) throws DataAccessException;
	public void delete(int SubjectId) throws DataAccessException;
	public SubjectVO selectOne(int SubjectId) throws DataAccessException;
}
