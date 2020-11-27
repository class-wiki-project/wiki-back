package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
	private int userId;
	private String email,password,studentName;
	private int studentNumber,auth;
}
