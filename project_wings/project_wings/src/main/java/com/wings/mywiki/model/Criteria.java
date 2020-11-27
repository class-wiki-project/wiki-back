package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int amount;
	private int page;
	private int startIndex;
	private int categoryId;
}