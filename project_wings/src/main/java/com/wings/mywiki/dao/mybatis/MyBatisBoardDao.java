package com.wings.mywiki.dao.mybatis;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.BoardDao;
import com.wings.mywiki.dao.mybatis.mapper.BoardMapper;
import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;

@Repository
public class MyBatisBoardDao implements BoardDao {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public BoardVO createPost(BoardVO vo) throws DataAccessException {
		return boardMapper.createPost(vo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO viewPostDetail(int postId) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardMapper.viewPostDetail(postId);
	}

	@Override
	public BoardVO updatePost(BoardVO vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardMapper.updatePost(vo);
	}

	@Override
	public void deletePost(int postId) throws DataAccessException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<BoardVO> listAll(Criteria cri) throws DataAccessException {
		// TODO Auto-generated method stub
		// boardMapper.listAllCategoryPost(categoryId);
		return boardMapper.listAll(cri);
	}

	@Override
	public void increaseViewCnt(int postId) throws DataAccessException {
		// TODO Auto-generated method stub
		boardMapper.increaseViewCnt(postId);
		
	}
	
	@Override
	public int getTotalCount(Criteria cri) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}
}
