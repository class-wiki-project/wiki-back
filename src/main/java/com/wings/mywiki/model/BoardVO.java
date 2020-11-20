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
	private int userId; // �ܷ�Ű
	private int subjectId; // �ܷ�Ű
	private String title, text, createDate, updateDate, deleteDate;
	private int hitNum;
}
