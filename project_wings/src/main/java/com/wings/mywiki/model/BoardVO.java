package com.wings.mywiki.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String title,text,createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updateDate;
	private int hitNum;
	private UsersVO usersVO;
	private SubjectVO subjectVO;
}