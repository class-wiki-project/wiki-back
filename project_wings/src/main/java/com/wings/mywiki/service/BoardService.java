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
		public void createPost(BoardVO board);
		//게시글 상세보기
		public BoardVO viewPostDetail(int postId);
		//게시글 수정
		public void updatePost(BoardVO board);
		//게시글 삭제
		public void deletePost(int postId);
		//특정 페이지에 해당하는 게시글 목록
		public List<BoardVO> listAll(Criteria cri);
		//게시글 조회수
		public void increaseViewCnt(int postId);
		//게시글 전체 데이터수
		public int getTotalCount(Criteria cri);
		//전체 과목 불러오기
		public List<SubjectVO> getSubjectList();
}
