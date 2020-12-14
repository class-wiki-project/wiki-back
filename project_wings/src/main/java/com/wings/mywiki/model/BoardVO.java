package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class BoardVO implements Serializable {
	
	private int boardId;
	private int userId;	// 외래키
	private int subjectId;	// 외래키
	private int categoryId;	// 외래키
	private String title,text,createDate,updateDate;
	private int hitNum;
	private UsersVO usersVO;
}