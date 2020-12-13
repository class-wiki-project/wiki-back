package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.wings.mywiki.dao.BoardDao;
import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public int createPost(HashMap<String, Object> map) {
		return boardDao.createPost(map);
		// TODO Auto-generated method stub
	}

	@Override
	public BoardVO viewPostDetail(int postId) {
		boardDao.increaseViewCnt(postId);
		return boardDao.viewPostDetail(postId);
	}

	@Override
	public void updatePost(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<BoardVO> listAll(Criteria cri) {
		// TODO Auto-generated method stub
		return boardDao.listAll(cri);
	}

	@Override
	public void increaseViewCnt(int postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardDao.getTotalCount(cri);
	}

	@Override
	public BoardVO getBoard(int boardId){
		// TODO Auto-generated method stub
		return boardDao.getBoard(boardId);
	}
	
	
	
}