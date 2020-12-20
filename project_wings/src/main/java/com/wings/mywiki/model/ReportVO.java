package com.wings.mywiki.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	private int reportId;
	private String reportUserEmail,reportedUserEmail,reportContent;
	private LocalDateTime reportedDate;
}
