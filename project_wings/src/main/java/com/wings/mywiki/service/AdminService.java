package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;
import com.wings.mywiki.model.ReportVO;

public interface AdminService {
	public List<ReportVO> getAllReports();
	public int createNotice(HashMap<String, Object> map);
	public void updateReportedNum(int reportUserId);
	public int getReportedNum(int reportUserId);
	public void deleteUser(int reportUserId);
	public void deleteReport(int reportId);
}
