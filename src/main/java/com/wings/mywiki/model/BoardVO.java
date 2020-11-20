package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO implements Serializable {
	private int boardId;
	private int userId; // 외래키
	private int subjectId; // 외래키
	private String title, text, createDate, updateDate, deleteDate;
	private int hitNum;
}
