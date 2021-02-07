package com.wings.mywiki.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdateVO implements Serializable{
	private String password;
	private String studentName;
	private String studentNumber;
	private String nickName;
	private String univName;
	private int userId;
}
