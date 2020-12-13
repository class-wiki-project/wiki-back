package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectVO implements Serializable {

	private int subjectId;
	private String subjectName,professor;
	private int year,semester;
}
