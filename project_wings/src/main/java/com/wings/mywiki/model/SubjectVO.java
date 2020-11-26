package com.wings.mywiki.model;

import java.io.Serializable;

public class SubjectVO implements Serializable{
	private int subjectId;
	private int userId;
	private String subjectName;
	private String professor;
	private int subjectYear, semester;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getSubjectYear() {
		return subjectYear;
	}
	public void setSubjectYear(int subjectYear) {
		this.subjectYear = subjectYear;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
}
