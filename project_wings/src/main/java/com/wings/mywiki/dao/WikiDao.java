package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.WikiVO;

public interface WikiDao {
	public WikiVO getWiki(int subjectId);
	public List<ClassificationVO> getClassification(int wikiId);
	public int editWiki(HashMap<String, String> map);
	public SubjectVO getSubject(int subjectId);
	public WikiVO getWikiByWikiId(int wikiId);
	public ClassificationVO getOneClassification(int wikiId);
	public int addClassification(HashMap<String, Object> map);
	public List<String> getAllGroupId();
}