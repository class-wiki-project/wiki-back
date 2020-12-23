package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.AdminDao;
import com.wings.mywiki.dao.mybatis.mapper.AdminMapper;
import com.wings.mywiki.dao.mybatis.mapper.BoardMapper;
import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.ReportVO;
import com.wings.mywiki.model.UsersVO;

@Repository
public class MyBatisAdminDao implements AdminDao{
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<ReportVO> getAllReports() {
		return adminMapper.getAllReports();
	}

	@Override
	public int createNotice(HashMap<String, Object> map) {
		return boardMapper.createPost(map);
	}

	@Override
	public void updateReportedNum(int reportUserId) {
		adminMapper.updateReportedNum(reportUserId);
	}

	@Override
	public int getReportedNum(int reportUserId) {
		return adminMapper.getReportedNum(reportUserId);
	}

	@Override
	public void deleteUser(int reportUserId) {
		adminMapper.deleteUser(reportUserId);
	}

	@Override
	public void deleteReport(int reportId) {
		adminMapper.deleteReport(reportId);
	}

	@Override
	public List<UsersVO> getAllUsers(Criteria cri) {
		return adminMapper.getAllUsers(cri);
	}

	@Override
	public List<BoardVO> getUsersPost(Criteria cri) {
		return adminMapper.getUsersPost(cri);
	}

	@Override
	public int getTotalCountByUserId(Criteria cri) {
		return adminMapper.getTotalCountByUserId(cri);
	}

	@Override
	public int getUserTotal(Criteria cri) {
		return adminMapper.getUserTotal(cri);
	}

}
