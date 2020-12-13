package com.wings.mywiki.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO implements Serializable {
	private int commentId;
	private int boardId,userId;	//외래키
	private String commentText;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date noticeDate;
}