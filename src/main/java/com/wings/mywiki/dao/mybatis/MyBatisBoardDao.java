package com.wings.mywiki.dao.mybatis;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.wings.mywiki.dao.BoardDao;
import com.wings.mywiki.dao.mybatis.mapper.BoardMapper;
import com.wings.mywiki.model.BoardVO;

@Repository
public class MyBatisBoardDao implements BoardDao {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void create(BoardVO vo) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO read(int postId) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardMapper.read(postId);
	}

	@Override
	public void update(BoardVO vo) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int postId) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardVO> listAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return boardMapper.listAll();
	}

	@Override
	public void increaseViewCnt(int postId, HttpSession session) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
}
