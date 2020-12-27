package com.wings.mywiki.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wings.mywiki.model.OnlinVO;

@Service
public interface OnlineService {
	public int Login(String keyId) throws DataAccessException;
	public void insert(OnlinVO push) throws DataAccessException;
	public void delete(String keyId) throws DataAccessException;
	public OnlinVO select(String keyId) throws DataAccessException;
}
