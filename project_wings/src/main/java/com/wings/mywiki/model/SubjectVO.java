package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectVO {
	private int subject_id;
	private int user_id;	//¿Ü·¡Å°
	private String subject_name,professor;
	private int year,semester;
}
