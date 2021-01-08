package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardService {
	//게시글 작성
		public int createPost(HashMap<String, Object> map);
		//게시글 상세보기
		public BoardVO viewPostDetail(int postId);
		//게시글 수정
		public int updatePost(HashMap<String, Object> map);
		//게시글 삭제
		public int deletePost(int postId);
		//특정 페이지에 해당하는 질문게시판 게시글 목록
		public List<BoardVO> listAll(Criteria cri);
		// 과목 정보가 필요없는 게시판 게시글 목록
		public List<BoardVO> listAllWithoutSubjectId(Criteria cri);
		//게시글 조회수
		public void increaseViewCnt(int postId);
		//게시글 전체 데이터수
		public int getTotalCount(Criteria cri);
		//해당 Board 객체 불러오기
		public BoardVO getBoard(int boardId);
		//신고 ) boardId로 userId 가져오기
		public int getUserIdByBoardId(int boardId);

}
