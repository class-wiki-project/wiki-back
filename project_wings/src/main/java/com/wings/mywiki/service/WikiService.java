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
	public int editWiki(HashMap<String, Object> map);
	public SubjectVO getSubject(int subjectId);
}