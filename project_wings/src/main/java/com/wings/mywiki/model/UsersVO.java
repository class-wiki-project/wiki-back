package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVO {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String email,password,studentName,univName,studentNumber;
	private int auth;
}