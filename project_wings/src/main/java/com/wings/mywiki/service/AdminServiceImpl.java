package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.AdminDao;
import com.wings.mywiki.dao.BoardDao;
import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.ReportVO;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<ReportVO> getAllReports() {
		return adminDao.getAllReports();
	}

	@Override
	public int createNotice(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.createPost(map);
	}

}
