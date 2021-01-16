package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wings.mywiki.dao.SubjectDao;
import com.wings.mywiki.model.SubjectVO;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDao subjectDao;
	

	@Override
	public List<SubjectVO> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return subjectDao.selectAll();
	}

	@Override
	public void update(SubjectVO subjectVO) throws DataAccessException {
		// TODO Auto-generated method stub
		subjectDao.update(subjectVO);
	}

	@Override
	public void delete(int SubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		subjectDao.delete(SubjectId);
	}

	@Override
	public SubjectVO selectOne(int SubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		return subjectDao.selectOne(SubjectId);
	}

	@Override
	public int addSubject(HashMap<String, Object> map) {
		return subjectDao.addSubject(map);
	}

	@Override
	public int getSubjectId(HashMap<String, Object> map) {
		return subjectDao.getSubjectId(map);
	}
}