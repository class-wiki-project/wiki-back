package com.wings.mywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.service.BoardServiceImpl;
@RestController
@RequestMapping("/board/*")
@CrossOrigin(origins="*", allowedHeaders="*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	// �Խñ� ��Ϻ���
	@GetMapping(value = "list")
	public Map<Integer, List<BoardVO>> list(@RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount,
			Criteria cri, HttpServletResponse response) throws IOException {
		
		
		// �Ķ���� ������ criteria ����
		cri.setAmount(amount); cri.setPage(page); cri.setCategoryId(categoryId);
		//�ش������� ���� �ε��� ����
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
	@PostMapping(value = "insert")
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
	@GetMapping(value = "viewDetail")
	public BoardVO viewDetail(@RequestParam(value="postId") int postId, HttpServletResponse response) throws IOException {
		
		// ��ȸ�� ����
		BoardVO board = boardServiceImpl.viewPostDetail(postId);
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		System.out.println("/board/viewDetail?=" + postId + " request accepted");
		return board;
	}
	
	// �Խñ� ����
	@PutMapping(value = "update")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestParam(value="postId") int postId, @RequestBody BoardVO board, HttpServletResponse response) throws IOException {
			
			boardServiceImpl.updatePost(board);
			if (board == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			System.out.println("post " + board.getPostId() + " updated.");
	}
	
	// �Խñ� ����
	@DeleteMapping(value = "delete")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam(value="postId") int postId, HttpServletResponse response) throws IOException {
			
		try {
			boardServiceImpl.deletePost(postId);
			System.out.println("board " + postId + " deleted.");
				
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
		}
	
	
}