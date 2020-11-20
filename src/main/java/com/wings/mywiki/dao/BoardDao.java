package com.wings.mywiki.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;

public interface BoardDao {
	//�Խñ� �ۼ�
	public void create(BoardVO vo) throws DataAccessException;
	//�Խñ� �󼼺���
	public BoardVO read(int postId) throws DataAccessException;
	//�Խñ� ����
	public void update(BoardVO vo) throws DataAccessException;
	//�Խñ� ����
	public void delete(int postId) throws DataAccessException;
	//�Խñ� ��ü��� ��ȸ
	public List<BoardVO> listAll() throws DataAccessException;
	//�Խñ� ��ȸ��
	public void increaseViewCnt(int postId, HttpSession session) throws DataAccessException;
}
