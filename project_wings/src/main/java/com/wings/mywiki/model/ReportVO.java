package com.wings.mywiki.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ReportVO implements Serializable{
	private int reportId,reportUserId,reportedUserId;
	private String reportContent;
	private String reportedDate;
	//private String reportUserEmail,reportedUserEmail;
	private UsersVO usersVO1;
	private UsersVO usersVO2;
	//private int reportedNum;
}
