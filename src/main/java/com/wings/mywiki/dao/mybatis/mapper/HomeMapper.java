package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import com.wings.mywiki.model.SubjectVO;

public interface HomeMapper {
	public List<SubjectVO> getAllSubjectByName(String searchName);
}
