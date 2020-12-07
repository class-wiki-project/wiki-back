package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectVO {
	private int subjectId;
	private String subjectName,professor;
	private int year,semester;
	
}
