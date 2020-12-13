package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO implements Serializable {
	private int commentId;
	private int boardId,userId;	//외래키
	private String noticeDate,commentText;
}