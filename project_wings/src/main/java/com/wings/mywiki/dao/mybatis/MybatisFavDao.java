package com.wings.mywiki.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.FavDao;
import com.wings.mywiki.dao.mybatis.mapper.FavMapper;
import com.wings.mywiki.model.FavVO;

@Repository
public class MybatisFavDao implements FavDao{
	
	@Autowired
	private FavMapper favMapper;
	
	@Override
	public void insert(FavVO favVO) throws DataAccessException {
		// TODO Auto-generated method stub
		favMapper.insert(favVO);
	}

	@Override
	public List<FavVO> selectAll(int userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return favMapper.selectAll(userId);
	}

	@Override
	public void update(FavVO favVO) throws DataAccessException {
		// TODO Auto-generated method stub
		favMapper.update(favVO);
	}

	@Override
	public void delete(int favSubjectId) throws DataAccessException {
		favMapper.delete(favSubjectId);
		
	}

	@Override
	public FavVO selectAll2(int favSubjectId) throws DataAccessException {
		// TODO Auto-generated method stub
		return favMapper.selectAll2(favSubjectId);
	}

	
}
