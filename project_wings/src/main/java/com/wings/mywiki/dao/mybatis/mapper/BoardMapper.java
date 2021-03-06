package com.wings.mywiki.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardMapper {
		//게시글 작성
		int createPost(HashMap<String, Object> map) throws DataAccessException;
		//게시글 상세보기
		BoardVO viewPostDetail(int postId) throws DataAccessException;
		//게시글 수정
		int updatePost(HashMap<String, Object> map) throws DataAccessException;
		//게시글 삭제
		int deletePost(int postId) throws DataAccessException;
		//특정 페이지에 해당하는 게시글 목록
		List<BoardVO> listAll(Criteria cri) throws DataAccessException;
		// 과목 정보 필요없는 게시글들 출력
		public List<BoardVO> listAllWithoutSubjectId(Criteria cri) throws DataAccessException;
		//게시글 조회수
		void increaseViewCnt(int postId) throws DataAccessException;
		//전체 게시글 갯수 계산
		int getTotalCount(Criteria cri) throws DataAccessException;
		//해당 Board 객체 불러오기
		BoardVO getBoard(int boardId) throws DataAccessException;
		int getUserIdByBoardId(int boardId);
}
