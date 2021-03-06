package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.SubjectDao;
import com.wings.mywiki.dao.mybatis.mapper.SubjectMapper;
import com.wings.mywiki.model.SubjectVO;

@Repository
public class MyBatisSubjectDao implements SubjectDao{

	@Autowired
	private SubjectMapper subjectMapper;
	

	@Override
	public List<SubjectVO> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return subjectMapper.selectAll();
	}

	@Override
	public void update(SubjectVO subjectVO) throws DataAccessException {
		// TODO Auto-generated method stub
		subjectMapper.update(subjectVO);
	}

	@Override
	public void delete(int SubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		subjectMapper.delete(SubjectId);
	}

	@Override
	public SubjectVO selectOne(int SubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		return subjectMapper.selectOne(SubjectId);
	}

	@Override
	public int addSubject(HashMap<String, Object> map) {
		return subjectMapper.addSubject(map);
	}

	@Override
	public int getSubjectId(HashMap<String, Object> map) {
		return subjectMapper.getSubjectId(map);
	}
}
