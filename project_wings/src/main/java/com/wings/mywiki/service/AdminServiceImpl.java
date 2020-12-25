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
import com.wings.mywiki.model.UsersVO;

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
		return boardDao.createPost(map);
	}

	@Override
	public void updateReportedNum(int reportUserId) {
		adminDao.updateReportedNum(reportUserId);
	}

	@Override
	public int getReportedNum(int reportUserId) {
		return adminDao.getReportedNum(reportUserId);
	}

	@Override
	public void deleteUser(int reportUserId) {
		adminDao.deleteUser(reportUserId);
	}

	@Override
	public void deleteReport(int reportId) {
		adminDao.deleteReport(reportId);
	}

	@Override
	public List<UsersVO> getAllUsers(Criteria cri) {
		return adminDao.getAllUsers(cri);
	}

	@Override
	public List<BoardVO> getUsersPost(Criteria cri) {
		return adminDao.getUsersPost(cri);
	}

	@Override
	public int getTotalCountByUserId(Criteria cri) {
		return adminDao.getTotalCountByUserId(cri);
	}

	@Override
	public int getUserTotal(Criteria cri) {
		return adminDao.getUserTotal(cri);
	}
	
	@Override
	public int getReportedUserId(int reportId) {
		return adminDao.getReportedUserId(reportId);
	}

}
