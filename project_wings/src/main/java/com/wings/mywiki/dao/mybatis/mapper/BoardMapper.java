package com.wings.mywiki.dao.mybatis.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;

public interface BoardMapper {
		//�Խñ� �ۼ�
		BoardVO createPost(BoardVO vo) throws DataAccessException;
		//�Խñ� �󼼺���
		BoardVO viewPostDetail(int postId) throws DataAccessException;
		//�Խñ� ����
		BoardVO updatePost(BoardVO vo) throws DataAccessException;
		//�Խñ� ����
		BoardVO deletePost(int postId) throws DataAccessException;
		//Ư�� �������� �ش��ϴ� �Խñ� ���
		List<BoardVO> listAll(Criteria cri) throws DataAccessException;
		//�Խñ� ��ȸ��
		void increaseViewCnt(int postId) throws DataAccessException;
		//��ü �Խñ� ���� ���
		int getTotalCount(Criteria cri) throws DataAccessException;
}
