package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WikiVO {
	private int wiki_id;
	private int subject_id;		// ¿Ü·¡Å°
	private String updateDate;
}
