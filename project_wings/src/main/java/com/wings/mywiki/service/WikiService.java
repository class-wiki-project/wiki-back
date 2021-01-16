package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.WikiVO;

@Service
public interface WikiService {
	public WikiVO getWiki(int subjectId);
	public List<ClassificationVO> getClassification(int wikiId);
	public int editWiki(HashMap<String, String> map);
	public SubjectVO getSubject(int subjectId);
	public WikiVO getWikiByWikiId(int wikiId);
	public ClassificationVO getOneClassification(int wikiId);
	public int addClassification(HashMap<String, Object> map);
	public List<String> getAllGroupId(int wikiId);
	public int addWiki(HashMap<String, Object> map);
}