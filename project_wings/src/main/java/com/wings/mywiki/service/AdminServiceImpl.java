package com.wings.mywiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.AdminDao;
import com.wings.mywiki.model.ReportVO;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;

	@Override
	public List<ReportVO> getAllReports() {
		return adminDao.getAllReports();
	}

}
