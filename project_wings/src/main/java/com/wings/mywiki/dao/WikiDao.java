package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.WikiVO;

public interface WikiDao {
	public WikiVO getWiki(int subjectId);
	public List<ClassificationVO> getClassification(int wikiId);
	public int editWiki(HashMap<String, String> map);
}
