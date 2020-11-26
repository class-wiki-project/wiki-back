package com.wings.mywiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.FavDao;
import com.wings.mywiki.model.FavVO;

@Service
@Transactional
public class FavServiceImpl implements FavService{
	@Autowired
	private FavDao favDao;
	
	@Override
	public void insert(FavVO favVO) throws DataAccessException {
		favDao.insert(favVO);
	}

	@Override
	public List<FavVO> selectAll(int userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return favDao.selectAll(userId);
	}

	@Override
	public void update(FavVO favVO) throws DataAccessException {
		// TODO Auto-generated method stub
		favDao.update(favVO);
	}

	@Override
	public void delete(int favSubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		favDao.delete(favSubjectId);
	}

	@Override
	public FavVO selectAll2(int favSubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		return favDao.selectAll2(favSubjectId);
	}

}
