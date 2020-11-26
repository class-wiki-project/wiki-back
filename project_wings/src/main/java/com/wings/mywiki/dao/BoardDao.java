package com.wings.mywiki.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;

public interface BoardDao {
	//�Խñ� �ۼ�
	public BoardVO createPost(BoardVO vo) throws DataAccessException;
	//�Խñ� �󼼺���
	public BoardVO viewPostDetail(int postId) throws DataAccessException;
	//�Խñ� ����
	public BoardVO updatePost(BoardVO vo) throws DataAccessException;
	//�Խñ� ����
	public void deletePost(int postId) throws DataAccessException;
	//�Խñ� ��ü��� ��ȸ
	public List<BoardVO> listAll(Criteria cri) throws DataAccessException;
	//�Խñ� ��ȸ��
	public void increaseViewCnt(int postId) throws DataAccessException;
	//�Խñ� ��ü �����ͼ�
	public int getTotalCount(Criteria cri) throws DataAccessException;
}
