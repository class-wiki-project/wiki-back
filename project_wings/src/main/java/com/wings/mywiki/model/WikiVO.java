package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WikiVO {
	private int wikiId;
	private int subjectId;		//¿Ü·¡Å°
	private String updateDate;
}