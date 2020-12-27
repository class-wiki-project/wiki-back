package com.wings.mywiki.model;

import java.io.Serializable;
import java.util.List;

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
	private String reportUserEmail,reportedUserEmail;
	private int reportedNum;
}
