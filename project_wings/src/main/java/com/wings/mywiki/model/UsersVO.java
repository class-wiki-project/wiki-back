package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVO implements Serializable {

	private int userId;
	private String email,password,studentName,univName,studentNumber;
	private int auth,reportedNum;
}