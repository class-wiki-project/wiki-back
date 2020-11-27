package com.wings.mywiki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wings.mywiki.model.SubjectVO;

@Service
public interface HomeService {
	List<SubjectVO> getAllSubjectByName(String searchName);
}
