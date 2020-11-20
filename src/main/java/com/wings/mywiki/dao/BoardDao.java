package com.wings.mywiki.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;

public interface BoardDao {
	//게시글 작성
	public void create(BoardVO vo) throws DataAccessException;
	//게시글 상세보기
	public BoardVO read(int postId) throws DataAccessException;
	//게시글 수정
	public void update(BoardVO vo) throws DataAccessException;
	//게시글 삭제
	public void delete(int postId) throws DataAccessException;
	//게시글 전체목록 조회
	public List<BoardVO> listAll() throws DataAccessException;
	//게시글 조회수
	public void increaseViewCnt(int postId, HttpSession session) throws DataAccessException;
}
