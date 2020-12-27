package com.wings.mywiki.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wings.mywiki.model.CommentVO;
import com.wings.mywiki.service.CommentService;

@Controller
@RequestMapping("/board")
@SessionAttributes("loginSession")
public class CommentController {
   @Autowired
   private CommentService commentService;

 //紐⑤뱺 �뙎湲� 蹂닿린
   @RequestMapping(value = "/showComments", method = RequestMethod.GET, produces = "application/json; charset=utf8")
   @ResponseBody
   public HashMap<String, Object> getComments(@RequestParam int boardId) {
      HashMap<String, Object> map = new HashMap<String, Object>();
      List<CommentVO> commentList= commentService.getComments(boardId);
      //changeClass(commentList);
      map.put("commentList", commentList);

      return map;
   }

// �뙎湲� �엯�젰
   @RequestMapping(value = "/inputComment", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   public @ResponseBody HashMap<String, Object> inputComment(@RequestBody HashMap<String, Object> map) {
	   if (commentService.inputComment(map) == 0) { // �꽦怨�:1, �떎�뙣:0
         System.out.println("inputing comment cannot be done!");
      } else {
         System.out.println("Success!!!");
      }
      HashMap<String, Object> commentMap = new HashMap<String, Object>();
      List<CommentVO> commentList= commentService.getComments((int) map.get("boardId"));
      //changeClass(commentList);
      commentMap.put("commentList", commentList);
		  
      return commentMap;
   }
   
// �뙎湲� �닔�젙
   @RequestMapping(value = "/updateComment", method = RequestMethod.PUT, produces = "application/json; charset=utf8")
   public @ResponseBody HashMap<String, Object> updateComment(@RequestBody HashMap<String, Object> map) {
      if (commentService.updateComment(map) == 0) { // �꽦怨�:1, �떎�뙣:0
         System.out.println("Updating comment cannot be done!");
      } else {
         System.out.println("Success!!!");
      }
      HashMap<String, Object> commentMap = new HashMap<String, Object>();
      List<CommentVO> commentList= commentService.getComments((int) map.get("boardId"));
      //changeClass(commentList);
      commentMap.put("commentList", commentList);
  
      return commentMap;
   }

// �뙎湲� �궘�젣
   @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE, produces = "application/json; charset=utf8")
   public @ResponseBody HashMap<String, Object> deleteComment(@RequestBody HashMap<String, Object> map) {
      int commentId = (int) map.get("commentId");
      int boardId = (int) map.get("boardId");

      if (commentService.deleteComment(commentId) == 0) { // �꽦怨�:1, �떎�뙣:0
         System.out.println("deleting comment cannot be done!");
      } else {
         System.out.println("Success!!!");
      }

      HashMap<String, Object> commentMap = new HashMap<String, Object>();
      List<CommentVO> commentList = commentService.getComments(boardId);
      //changeClass(commentList);

      commentMap.put("commentList", commentList);

    //�궘�젣 �썑 蹂�寃� �맂 comments�뱾 諛섑솚
      return commentMap;
   }
   
   /*
   public List<CommentVO> changeClass(List<CommentVO> commentList){
	   for(Object vo : commentList) {
	    	  for(Entry<String, Object> element : ((HashMap<String, Object>) vo).entrySet()){
	    		  if (element.getKey().equals("noticeDate")) {
	    			  Date date = (Date) element.getValue();
	    			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    			  String noticeDate = format.format(date);
	    			  ((HashMap<String, Object>) vo).put("noticeDate",noticeDate);
	    		  }
	          }
	      }
	   return commentList;
   }
   */
}