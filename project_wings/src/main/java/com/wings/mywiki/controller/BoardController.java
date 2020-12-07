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
import com.wings.mywiki.model.ClassificationVO;
import com.wings.mywiki.model.Criteria;
import com.wings.mywiki.model.SubjectVO;
import com.wings.mywiki.model.WikiVO;
import com.wings.mywiki.service.BoardServiceImpl;
import com.wings.mywiki.service.CommentServiceImpl;
@RestController
@RequestMapping("/board/*")
@CrossOrigin(origins="*", allowedHeaders="*")
public class BoardController {
	
	//@RequestBody: json을 객체로
	//@ResponseBody: 객체를 json으로
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	// 게시글 목록보기
	@GetMapping(value = "list")
	public HashMap<String, Object> list(@RequestParam(value="subjectId", required=false) Integer subjectId, @RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount,
			Criteria cri, HttpServletResponse response) throws IOException {
		
		// 파라미터 값으로 criteria 설정
		cri.setAmount(amount); cri.setPage(page); cri.setCategoryId(categoryId); cri.setSubjectId(subjectId);
		//해당페이지 시작 인덱스 설정
		cri.setStartIndex((page-1)*amount); 
		
		System.out.println("/board/list?subjectId=" + cri.getSubjectId() + "&categoryId=" + categoryId + "&page=" + page + "&amount=" + cri.getAmount() + "&startIndex=" + cri.getStartIndex() +" request accepted");
		
		// 해당 게시판 전체 게시글 불러오기
		List<BoardVO> boardList = boardServiceImpl.listAll(cri);
		
		if (boardList == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// 해당 게시판 전체 게시글 수 불러오기
		int totalCount = boardServiceImpl.getTotalCount(cri);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("TotalCount", totalCount);
		map.put("BoardMap", boardList);

		return map;
	}
	
	// 게시글 작성 폼 불러오기
	@GetMapping(value = "insert")
	@ResponseStatus(HttpStatus.OK)
	public List<SubjectVO> getPostForm(HttpServletResponse response) throws IOException {
			
		List<SubjectVO> subject = boardServiceImpl.getSubjectList();
		if (subject != null) { // subjectList를 성공적으로 받아오면
			System.out.println("Getting subjectList success!!!");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		return subject;
	}
	
	// 게시글 작성처리
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insert(@RequestBody HashMap<String, Object> map, HttpServletResponse response) throws IOException {
		
		if (boardServiceImpl.createPost(map) == 1) { // 성공 1, 실패 0
			System.out.println("board 게시물 " + map.get("userId") + " created.");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return map;
	}
	
	// 게시글 상세보기 -> 상세보기 클릭하면 조회 수 증가
	@GetMapping(value = "viewDetail")
	public HashMap<String, Object> viewDetail(@RequestParam(value="boardId") int boardId, HttpServletResponse response) throws IOException {
		System.out.println("/board/viewDetail?=" + boardId + " request accepted");
		
		// 특정 게시글 상세보기 (조회수 증가 기능 포함)
		BoardVO board = boardServiceImpl.viewPostDetail(boardId);
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 해당 게시글 객체
		map.put("BoardVO", board);
		// 해당 게시글에 해당하는 모든 댓글 불러오기 
		map.put("CommentMap", commentServiceImpl.getComments(boardId));
		
		return map;
	}
	
	// 게시글 수정
	@PutMapping(value = "update")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> update(@RequestBody HashMap<String, Object> map, HttpServletResponse response) throws IOException {
			
			
			boardServiceImpl.updatePost(map);
			if (map == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			System.out.println("post " + map.get("boardId") + " updated.");
			
			HashMap<String, Object> update = new HashMap<String, Object>();
			// 해당 게시글 객체
			update.put("BoardVO", boardServiceImpl.getBoard((int)map.get("boardId")));
			// 해당 게시글에 해당하는 모든 댓글 불러오기 
			update.put("CommentMap", commentServiceImpl.getComments((int)map.get("boardId")));
			
			return update;
	}
	
	// 게시글 삭제
	@DeleteMapping(value = "delete")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam(value="boardId") int boardId, HttpServletResponse response) throws IOException {
			
		try {
			boardServiceImpl.deletePost(boardId);
			System.out.println("board " + boardId + " deleted.");
				
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
		}
	
	
}