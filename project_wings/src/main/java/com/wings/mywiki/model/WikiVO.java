package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WikiVO {
	private static final long serialVersionUID = 1L;
	private int wikiId;
	private int subjectId;		//¿Ü·¡Å°
	private String updateDate;
}