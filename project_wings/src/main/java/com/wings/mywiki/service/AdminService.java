package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.ReportVO;
import com.wings.mywiki.model.UsersVO;

public interface AdminService {
	public List<ReportVO> getAllReports();
	public int createNotice(HashMap<String, Object> map);
	public void updateReportedNum(int reportUserId);
	public int getReportedNum(int reportUserId);
	public void deleteUser(int reportUserId);
	public void deleteReport(int reportId);
	public List<UsersVO> getAllUsers(Criteria cri);
	public List<BoardVO> getUsersPost(Criteria cri);
	public int getTotalCountByUserId(Criteria cri);
	public int getUserTotal(Criteria cri);
}
