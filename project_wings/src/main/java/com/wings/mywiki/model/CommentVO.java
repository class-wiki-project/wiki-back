package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
	private int commentId;
	private int boardId,userId,subjectId;	// ¿Ü·¡Å°
	private String noticeDate,commentText;
}
