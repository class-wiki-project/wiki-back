package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria implements Serializable {
	private int amount;
	private int page;
	private int startIndex;
	private int categoryId;
	private int userId;
	private Integer subjectId;
}