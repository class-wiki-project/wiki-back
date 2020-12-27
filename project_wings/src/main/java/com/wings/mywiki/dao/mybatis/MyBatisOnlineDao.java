package com.wings.mywiki.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.OnlineDao;
import com.wings.mywiki.dao.mybatis.mapper.OnlineMapper;
import com.wings.mywiki.model.OnlinVO;

@Repository
public class MyBatisOnlineDao implements OnlineDao{

	@Autowired
	private OnlineMapper onlineMapper;
	
	@Override
	public int Login(String keyId) {
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
