package com.wings.mywiki.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.wings.mywiki.model.BoardVO;

public interface BoardService {
	//게시글 작성
		public void create(BoardVO vo) throws Exception;
		//게시글 상세보기
		public BoardVO read(int postId) throws Exception;
		//게시글 수정
		public void update(BoardVO vo) throws Exception;
		//게시글 삭제
		public void delete(int postId) throws Exception;
		//게시글 전체목록 조회
		public List<BoardVO> listAll() throws Exception;
		//게시글 조회수
		public void increaseViewCnt(int postId, HttpSession session) throws Exception;
}
