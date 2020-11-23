package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;

public interface BoardMapper {
		//게시글 작성
		BoardVO createPost(BoardVO vo) throws DataAccessException;
		//게시글 상세보기
		BoardVO viewPostDetail(int postId) throws DataAccessException;
		//게시글 수정
		BoardVO updatePost(BoardVO vo) throws DataAccessException;
		//게시글 삭제
		BoardVO deletePost(int postId) throws DataAccessException;
		//특정 페이지에 해당하는 게시글 목록
		List<BoardVO> listAll(Criteria cri) throws DataAccessException;
		//게시글 조회수
		void increaseViewCnt(int postId) throws DataAccessException;
		//전체 게시글 갯수 계산
		int getTotalCount(Criteria cri) throws DataAccessException;
}
