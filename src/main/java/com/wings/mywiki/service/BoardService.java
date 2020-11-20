package com.wings.mywiki.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.wings.mywiki.model.BoardVO;

public interface BoardService {
	//�Խñ� �ۼ�
		public void create(BoardVO vo) throws Exception;
		//�Խñ� �󼼺���
		public BoardVO read(int postId) throws Exception;
		//�Խñ� ����
		public void update(BoardVO vo) throws Exception;
		//�Խñ� ����
		public void delete(int postId) throws Exception;
		//�Խñ� ��ü��� ��ȸ
		public List<BoardVO> listAll() throws Exception;
		//�Խñ� ��ȸ��
		public void increaseViewCnt(int postId, HttpSession session) throws Exception;
}
