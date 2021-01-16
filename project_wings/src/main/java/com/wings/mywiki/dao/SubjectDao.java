package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.SubjectVO;

public interface SubjectDao {
	public List<SubjectVO> selectAll() throws DataAccessException;
	public void update(SubjectVO subjectVO) throws DataAccessException;
	public void delete(int SubjectId) throws DataAccessException;
	public SubjectVO selectOne(int SubjectId) throws DataAccessException;
	public int addSubject(HashMap<String, Object> map);
	public int getSubjectId(HashMap<String, Object> map);
}