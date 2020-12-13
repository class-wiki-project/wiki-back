package com.wings.mywiki.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardService {
	//�Խñ� �ۼ�
		public int createPost(HashMap<String, Object> map);
		//�Խñ� �󼼺���
		public BoardVO viewPostDetail(int postId);
		//�Խñ� ����
		public void updatePost(HashMap<String, Object> map);
		//�Խñ� ����
		public void deletePost(int postId);
		//Ư�� �������� �ش��ϴ� �Խñ� ���
		public List<BoardVO> listAll(Criteria cri);
		//�Խñ� ��ȸ��
		public void increaseViewCnt(int postId);
		//�Խñ� ��ü �����ͼ�
		public int getTotalCount(Criteria cri);
		//�ش� Board ��ü �ҷ�����
		public BoardVO getBoard(int boardId);

}
