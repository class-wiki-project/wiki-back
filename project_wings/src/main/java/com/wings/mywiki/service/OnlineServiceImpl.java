package com.wings.mywiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.mybatis.mapper.OnlineMapper;
import com.wings.mywiki.model.OnlinVO;
@Service
@Transactional
public class OnlineServiceImpl implements OnlineService{
	@Autowired
	private OnlineMapper onlineMapper;
	
	@Override
	public int Login(String keyId) throws DataAccessException {
		// TODO Auto-generated method stub
		return onlineMapper.Login(keyId);
	}

	@Override
	public void insert(OnlinVO push) throws DataAccessException {
		// TODO Auto-generated method stub
		onlineMapper.insert(push);
		
	}

	@Override
	public void delete(String keyId) throws DataAccessException {
		// TODO Auto-generated method stub
		onlineMapper.delete(keyId);
	}

	@Override
	public OnlinVO select(String keyId) throws DataAccessException {
		return onlineMapper.select(keyId);
		
	}

}
