package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardDao {
	public int createPost(HashMap<String, Object> map) throws DataAccessException;
	public BoardVO viewPostDetail(int postId) throws DataAccessException;
	public int updatePost(HashMap<String, Object> map) throws DataAccessException;
	public void deletePost(int postId) throws DataAccessException;
	public List<BoardVO> listAll(Criteria cri) throws DataAccessException;
	public void increaseViewCnt(int postId) throws DataAccessException;
	public int getTotalCount(Criteria cri) throws DataAccessException;
	public BoardVO getBoard(int boardId) throws DataAccessException;
	public int getUserIdByBoardId(int boardId);
}
