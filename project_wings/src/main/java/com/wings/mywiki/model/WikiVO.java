package com.wings.mywiki.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WikiVO implements Serializable {
	private int wikiId;
	private int subjectId;		//외래키
	private String updateDate;
}