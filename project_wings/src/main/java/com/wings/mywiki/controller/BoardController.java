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
	
	//@RequestBody: json�� ��ü��
	//@ResponseBody: ��ü�� json����
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	// �Խñ� ��Ϻ���
	@GetMapping(value = "list")
	public HashMap<String, Object> list(@RequestParam(value="subjectId", required=false) Integer subjectId, @RequestParam(value="categoryId") int categoryId, @RequestParam(value="page") int page, @RequestParam(value="amount") int amount,
			Criteria cri, HttpServletResponse response) throws IOException {
		
		// �Ķ���� ������ criteria ����
		cri.setAmount(amount); cri.setPage(page); cri.setCategoryId(categoryId); cri.setSubjectId(subjectId);
		//�ش������� ���� �ε��� ����
		cri.setStartIndex((page-1)*amount); 
		
		System.out.println("/board/list?subjectId=" + cri.getSubjectId() + "&categoryId=" + categoryId + "&page=" + page + "&amount=" + cri.getAmount() + "&startIndex=" + cri.getStartIndex() +" request accepted");
		
		// �ش� �Խ��� ��ü �Խñ� �ҷ�����
		List<BoardVO> boardList = boardServiceImpl.listAll(cri);
		
		if (boardList == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// �ش� �Խ��� ��ü �Խñ� �� �ҷ�����
		int totalCount = boardServiceImpl.getTotalCount(cri);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("TotalCount", totalCount);
		map.put("BoardMap", boardList);

		return map;
	}
	
	// �Խñ� �ۼ� �� �ҷ�����
	@GetMapping(value = "insert")
	@ResponseStatus(HttpStatus.OK)
	public List<SubjectVO> getPostForm(HttpServletResponse response) throws IOException {
			
		List<SubjectVO> subject = boardServiceImpl.getSubjectList();
		if (subject != null) { // subjectList�� ���������� �޾ƿ���
			System.out.println("Getting subjectList success!!!");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		return subject;
	}
	
	// �Խñ� �ۼ�ó��
	@PostMapping(value = "insert")
	@ResponseStatus(HttpStatus.CREATED)
	public HashMap<String, Object> insert(@RequestBody HashMap<String, Object> map, HttpServletResponse response) throws IOException {
		
		if (boardServiceImpl.createPost(map) == 1) { // ���� 1, ���� 0
			System.out.println("board �Խù� " + map.get("userId") + " created.");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return map;
	}
	
	// �Խñ� �󼼺��� -> �󼼺��� Ŭ���ϸ� ��ȸ �� ����
	@GetMapping(value = "viewDetail")
	public HashMap<String, Object> viewDetail(@RequestParam(value="boardId") int boardId, HttpServletResponse response) throws IOException {
		System.out.println("/board/viewDetail?=" + boardId + " request accepted");
		
		// Ư�� �Խñ� �󼼺��� (��ȸ�� ���� ��� ����)
		BoardVO board = boardServiceImpl.viewPostDetail(boardId);
		if (board == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		// �ش� �Խñ� ��ü
		map.put("BoardVO", board);
		// �ش� �Խñۿ� �ش��ϴ� ��� ��� �ҷ����� 
		map.put("CommentMap", commentServiceImpl.getComments(boardId));
		
		return map;
	}
	
	// �Խñ� ����
	@PutMapping(value = "update")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, Object> update(@RequestBody HashMap<String, Object> map, HttpServletResponse response) throws IOException {
			
			
			boardServiceImpl.updatePost(map);
			if (map == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			System.out.println("post " + map.get("boardId") + " updated.");
			
			HashMap<String, Object> update = new HashMap<String, Object>();
			// �ش� �Խñ� ��ü
			update.put("BoardVO", boardServiceImpl.getBoard((int)map.get("boardId")));
			// �ش� �Խñۿ� �ش��ϴ� ��� ��� �ҷ����� 
			update.put("CommentMap", commentServiceImpl.getComments((int)map.get("boardId")));
			
			return update;
	}
	
	// �Խñ� ����
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