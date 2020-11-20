package com.wings.mywiki.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.WikiDao;
import com.wings.mywiki.dao.mybatis.mapper.WikiMapper;
import com.wings.mywiki.model.ClassificationVO;
//import com.wings.mywiki.dao.mybatis.mapper.WikiMapper;
import com.wings.mywiki.model.WikiVO;

@Repository
public class MyBatisWikiDao implements WikiDao{
	@Autowired
	private WikiMapper wikiMapper;
	
	//subject_id�� wikiVO ���� ��
	public WikiVO getWiki(int subjectId) {
		return wikiMapper.getWiki(subjectId);
	}
	
	//wiki_id�� ClassificationVO ���� ��
	public List<ClassificationVO> getClassification(int wikiId) {
		return wikiMapper.getClassification(wikiId);
	}

	//wiki ����
	public int editWiki(HashMap<String, String> map) {	
		return wikiMapper.editWiki(map);
	}
}
