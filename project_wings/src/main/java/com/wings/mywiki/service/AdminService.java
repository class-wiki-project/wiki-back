package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;
import com.wings.mywiki.model.ReportVO;

public interface AdminService {
	public List<ReportVO> getAllReports();
	public int createNotice(HashMap<String, Object> map);
}
