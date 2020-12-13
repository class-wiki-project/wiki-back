package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class FavVO implements Serializable{
	private int favSubjectId;
	private int userId;
	private int subjectId;
	private String subjectName;
	private String iconName;
	private String professor;
}