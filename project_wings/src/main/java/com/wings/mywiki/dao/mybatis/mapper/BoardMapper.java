package com.wings.mywiki.dao.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardMapper {
		//�Խñ� �ۼ�
		int createPost(HashMap<String, Object> map) throws DataAccessException;
		//�Խñ� �󼼺���
		BoardVO viewPostDetail(int postId) throws DataAccessException;
		//�Խñ� ����
		void updatePost(HashMap<String, Object> map) throws DataAccessException;
		//�Խñ� ����
		void deletePost(int postId) throws DataAccessException;
		//Ư�� �������� �ش��ϴ� �Խñ� ���
		List<BoardVO> listAll(Criteria cri) throws DataAccessException;
		//�Խñ� ��ȸ��
		void increaseViewCnt(int postId) throws DataAccessException;
		//��ü �Խñ� ���� ���
		int getTotalCount(Criteria cri) throws DataAccessException;
		//�ش� Board ��ü �ҷ�����
		BoardVO getBoard(int boardId) throws DataAccessException;
}
