package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	// �Խñ� ��Ϻ���
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<Integer, List<BoardVO>> list(@RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount,
			Criteria cri, HttpServletResponse response) throws IOException {
		
		
		// �Ķ���� ������ criteria ����
		cri.setAmount(amount); cri.setPage(page); cri.setCategoryId(categoryId);
		// 1�������� 0�ε������� ����
		if (page==1)
			cri.setStartIndex(0);
		else
			cri.setStartIndex((page-1)*amount); 
		
		System.out.println("/board/list?categoryId=" + cri.getCategoryId() + "&page=" + cri.getPage() + "&amount=" + cri.getAmount() + "&startIndex=" + cri.getStartIndex() +" request accepted");
		
		List<BoardVO> list = boardServiceImpl.listAll(cri);
		if (list == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		// �Խù� �� ���� ��������
		int total = boardServiceImpl.getTotalCount(cri);
		
		// ��ü �Խù� ���� Paging ���� ������ ���� Map
		Map<Integer, List<BoardVO>> map = new HashMap<Integer,List<BoardVO>>();
		map.put(total, list);
		
		return map;
	}
	
	// �Խñ� �ۼ�ó��
	@RequestMapping(value="insert", method=RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public BoardVO insert(@RequestBody BoardVO board, HttpServletResponse response) throws IOException {
		boardServiceImpl.createPost(board);
		
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		System.out.println("board " + board.getPostId() + " created.");
		return board;
	}
	
	// �Խñ� �󼼺��� -> �󼼺��� Ŭ���ϸ� ��ȸ �� ����
	@RequestMapping(value="viewDetail", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public BoardVO viewDetail(@RequestParam(value="postId") int postId, HttpServletResponse response) throws IOException {
		System.out.println("/board/viewDetail?=" + postId + " request accepted");
		// ��ȸ�� ����
		BoardVO board = boardServiceImpl.viewPostDetail(postId);
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return board;
	}
	
	// �Խñ� ����
	@RequestMapping(value="update", method=RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestParam(value="postId") int postId, @RequestBody BoardVO board, HttpServletResponse response) throws IOException {
			
			if (board == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			boardServiceImpl.updatePost(board);
			System.out.println("post " + board.getPostId() + " updated.");
	}
	
	// �Խñ� ����
	@RequestMapping(value="delete", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam(value="postId") int postId, HttpServletResponse response) throws IOException {

			BoardVO board = boardServiceImpl.deletePost(postId);
			if (board == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			System.out.println("board " + board.getPostId() + " deleted.");
		}
	
	

}
