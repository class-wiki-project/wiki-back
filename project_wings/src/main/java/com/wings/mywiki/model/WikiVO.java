package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WikiVO implements Serializable {
	private int wikiId;
	private int subjectId;		//¿Ü·¡Å°
	private String updateDate;
}