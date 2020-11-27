package com.wings.mywiki.dao;

import java.util.List;

import com.wings.mywiki.model.SubjectVO;

public interface HomeDao {
	List<SubjectVO> getAllSubjectByName(String searchName);
}
