package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class BoardVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int postId;
	private int userId;	// �ܷ�Ű
	private int subjectId;	// �ܷ�Ű
	private int categoryId;	// �ܷ�Ű
	private String title,text,createDate,updateDate;
	private int hitNum;

	
}
