package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardDao {
	//게시글 작성
	public void createPost(BoardVO board) throws DataAccessException;
	//게시글 상세보기
	public BoardVO viewPostDetail(int postId) throws DataAccessException;
	//게시글 수정
	public void updatePost(BoardVO board) throws DataAccessException;
	//게시글 삭제
	public void deletePost(int postId) throws DataAccessException;
	//게시글 전체목록 조회
	public List<BoardVO> listAll(Criteria cri) throws DataAccessException;
	//게시글 조회수
	public void increaseViewCnt(int postId) throws DataAccessException;
	//게시글 전체 데이터수
	public int getTotalCount(Criteria cri) throws DataAccessException;
	//게시글 전체 데이터수
	public List<SubjectVO> getSubjectList() throws DataAccessException;
}
