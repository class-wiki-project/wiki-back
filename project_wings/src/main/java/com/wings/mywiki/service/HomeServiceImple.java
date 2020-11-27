package com.wings.mywiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.HomeDao;
import com.wings.mywiki.model.SubjectVO;

@Service
@Transactional
public class HomeServiceImple implements HomeService{
	@Autowired
	private HomeDao homeDao;

	@Override
	public List<SubjectVO> getAllSubjectByName(String searchName) {
		return homeDao.getAllSubjectByName(searchName);
	}
}
