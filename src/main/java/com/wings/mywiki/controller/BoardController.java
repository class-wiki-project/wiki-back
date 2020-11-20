package com.wings.mywiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wings.mywiki.model.BoardVO;
import com.wings.mywiki.service.BoardService;
import com.wings.mywiki.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	// 게시글 목록보기
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception {
		List<BoardVO> list = boardServiceImpl.listAll();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // 뷰를 list.jsp로 설정
		mav.addObject("list", list); // 데이터 저장
		return mav;
	}
	
	

}
