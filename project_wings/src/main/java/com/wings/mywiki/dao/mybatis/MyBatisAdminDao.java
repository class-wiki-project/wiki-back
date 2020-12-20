package com.wings.mywiki.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.AdminDao;
import com.wings.mywiki.dao.mybatis.mapper.AdminMapper;
import com.wings.mywiki.model.ReportVO;

@Repository
public class MyBatisAdminDao implements AdminDao{
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<ReportVO> getAllReports() {
		return adminMapper.getAllReports();
	}

}
