package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserOutVO implements Serializable {
	private int userId;
	private String email;
	private String studentName;
	private String studentNumber;
	private String univName;
}
