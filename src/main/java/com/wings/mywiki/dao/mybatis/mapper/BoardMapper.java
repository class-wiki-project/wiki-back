package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;

public interface BoardMapper {
	//게시글 작성
		void create(BoardVO vo) throws DataAccessException;
		//게시글 상세보기
		BoardVO read(int postId) throws DataAccessException;
		//게시글 수정
		void update(BoardVO vo) throws DataAccessException;
		//게시글 삭제
		void delete(int postId) throws DataAccessException;
		//게시글 전체목록 조회
		List<BoardVO> listAll() throws DataAccessException;
		//게시글 조회수
		void increaseViewCnt(int postId, HttpSession session) throws DataAccessException;
}
