package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.WikiDao;
import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.WikiVO;

@Service
@Transactional
public class WikiServiceImple implements WikiService{
	@Autowired
	private WikiDao wikiDao;

	//subject_id�� wikiVO ���� ��
	public WikiVO getWiki(int subjectId) {
		return wikiDao.getWiki(subjectId);
	}
	//wiki_id�� ClassificationVO ���� ��
	public List<ClassificationVO> getClassification(int wikiId) {
		return wikiDao.getClassification(wikiId);
	}
	
	//wiki ����
	public int editWiki(HashMap<String, String> map) {
		return wikiDao.editWiki(map);
	}
}
