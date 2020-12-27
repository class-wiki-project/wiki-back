package com.wings.mywiki.dao;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.OnlinVO;

public interface OnlineDao {
	public int Login(String keyId) throws DataAccessException;
	public void insert(OnlinVO push) throws DataAccessException;
	public void delete(String keyId) throws DataAccessException;
	public OnlinVO select(String keyId) throws DataAccessException;
}
