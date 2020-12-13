package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassificationVO implements Serializable {
	private int classificationId;
	private int userId;	//외래키
	private int wikiId;	//외래키
	private String title,text,groupId;
}