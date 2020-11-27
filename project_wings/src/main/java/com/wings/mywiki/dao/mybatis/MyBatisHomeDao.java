package com.wings.mywiki.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.HomeDao;
import com.wings.mywiki.dao.mybatis.mapper.HomeMapper;
import com.wings.mywiki.model.SubjectVO;

@Repository
public class MyBatisHomeDao implements HomeDao{
	@Autowired
	private HomeMapper homeMapper;

	@Override
	public List<SubjectVO> getAllSubjectByName(String searchName) {
		return homeMapper.getAllSubjectByName(searchName);
	}
}
