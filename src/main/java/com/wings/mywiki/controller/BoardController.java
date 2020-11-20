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
	
	// �Խñ� ��Ϻ���
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception {
		List<BoardVO> list = boardServiceImpl.listAll();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list"); // �並 list.jsp�� ����
		mav.addObject("list", list); // ������ ����
		return mav;
	}
	
	

}
