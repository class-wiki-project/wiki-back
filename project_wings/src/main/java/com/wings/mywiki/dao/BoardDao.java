package com.wings.mywiki.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;

public interface BoardDao {
	//�Խñ� �ۼ�
	public int createPost(HashMap<String, Object> map) throws DataAccessException;
	//�Խñ� �󼼺���
	public BoardVO viewPostDetail(int postId) throws DataAccessException;
	//�Խñ� ����
	public void updatePost(HashMap<String, Object> map) throws DataAccessException;
	//�Խñ� ����
	public void deletePost(int postId) throws DataAccessException;
	//�Խñ� ��ü��� ��ȸ
	public List<BoardVO> listAll(Criteria cri) throws DataAccessException;
	//�Խñ� ��ȸ��
	public void increaseViewCnt(int postId) throws DataAccessException;
	//�Խñ� ��ü �����ͼ�
	public int getTotalCount(Criteria cri) throws DataAccessException;
	//�ش� Board ��ü �ҷ�����
	public BoardVO getBoard(int boardId) throws DataAccessException;
}