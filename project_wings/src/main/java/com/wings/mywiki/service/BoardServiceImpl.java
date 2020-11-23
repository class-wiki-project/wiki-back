package com.wings.mywiki.service;

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

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public BoardVO createPost(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDao.createPost(vo);
	}

	@Override
	public BoardVO viewPostDetail(int postId) {
		// �󼼺��� Ŭ���ϸ� +1
		boardDao.increaseViewCnt(postId);
		return boardDao.viewPostDetail(postId);
	}

	@Override
	public BoardVO updatePost(BoardVO board) {
		// TODO Auto-generated method stub
		return boardDao.updatePost(board);
	}

	@Override
	public BoardVO deletePost(int postId) {
		// TODO Auto-generated method stub
		return boardDao.deletePost(postId);
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
	public int getTotalCount(Criteria cri) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardDao.getTotalCount(cri);
	}
	
	
	
}