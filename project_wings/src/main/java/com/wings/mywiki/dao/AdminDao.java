package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.ReportVO;

public interface AdminDao {

	public List<ReportVO> getAllReports();
	public int createNotice(HashMap<String, Object> map);

}
