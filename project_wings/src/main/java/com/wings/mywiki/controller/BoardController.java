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
	
	// 게시글 목록보기
	@GetMapping(value = "list")
	public Map<Integer, List<BoardVO>> list(@RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount,
			Criteria cri, HttpServletResponse response) throws IOException {
		
		
		// 파라미터 값으로 criteria 설정
		cri.setAmount(amount); cri.setPage(page); cri.setCategoryId(categoryId);
		//해당페이지 시작 인덱스 설정
		cri.setStartIndex((page-1)*amount); 
		
		System.out.println("/board/list?categoryId=" + cri.getCategoryId() + "&page=" + cri.getPage() + "&amount=" + cri.getAmount() + "&startIndex=" + cri.getStartIndex() +" request accepted");
		
		List<BoardVO> list = boardServiceImpl.listAll(cri);
		if (list == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		// 게시물 총 갯수 가져오기
		int total = boardServiceImpl.getTotalCount(cri);
		
		// 전체 게시물 수와 Paging 정보 전달을 위한 Map
		Map<Integer, List<BoardVO>> map = new HashMap<Integer,List<BoardVO>>();
		map.put(total, list);
		
		return map;
	}
	
	// 게시글 작성처리
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
	
	// 게시글 상세보기 -> 상세보기 클릭하면 조회 수 증가
	@GetMapping(value = "viewDetail")
	public BoardVO viewDetail(@RequestParam(value="postId") int postId, HttpServletResponse response) throws IOException {
		
		// 조회수 증가
		BoardVO board = boardServiceImpl.viewPostDetail(postId);
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		System.out.println("/board/viewDetail?=" + postId + " request accepted");
		return board;
	}
	
	// 게시글 수정
	@PutMapping(value = "update")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestParam(value="postId") int postId, @RequestBody BoardVO board, HttpServletResponse response) throws IOException {
			
			boardServiceImpl.updatePost(board);
			if (board == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			System.out.println("post " + board.getPostId() + " updated.");
	}
	
	// 게시글 삭제
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