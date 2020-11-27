package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassificationVO {
	private int classificationId;
	private int userId;	// �ܷ�Ű
	private int wikiId;	// �ܷ�Ű
	private String title,text,groupId;
}
