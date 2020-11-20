package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;

public interface BoardMapper {
	//�Խñ� �ۼ�
		void create(BoardVO vo) throws DataAccessException;
		//�Խñ� �󼼺���
		BoardVO read(int postId) throws DataAccessException;
		//�Խñ� ����
		void update(BoardVO vo) throws DataAccessException;
		//�Խñ� ����
		void delete(int postId) throws DataAccessException;
		//�Խñ� ��ü��� ��ȸ
		List<BoardVO> listAll() throws DataAccessException;
		//�Խñ� ��ȸ��
		void increaseViewCnt(int postId, HttpSession session) throws DataAccessException;
}
