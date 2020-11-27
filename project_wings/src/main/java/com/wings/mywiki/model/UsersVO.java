package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVO {
	private int userId;
	private String email,password,studentName,univName;
	private int studentNumber,auth;
}