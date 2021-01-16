package com.wings.mywiki.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.wings.mywiki.model.SubjectVO;

public interface SubjectMapper {

	List<SubjectVO> selectAll() throws DataAccessException;
	void update(SubjectVO subjectVO) throws DataAccessException;
	void delete(int SubjectId) throws DataAccessException;
	SubjectVO selectOne(int SubjectId) throws DataAccessException;
	int addSubject(HashMap<String, Object> map);
	int getSubjectId(HashMap<String, Object> map);
}
