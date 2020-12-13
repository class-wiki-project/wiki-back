package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.wings.mywiki.model.SubjectVO;

public interface SubjectMapper {

	void insert(SubjectVO subjectVO) throws DataAccessException;
	List<SubjectVO> selectAll() throws DataAccessException;
	void update(SubjectVO subjectVO) throws DataAccessException;
	void delete(int SubjectId) throws DataAccessException;
}