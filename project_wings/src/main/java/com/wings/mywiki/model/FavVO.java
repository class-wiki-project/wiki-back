package com.wings.mywiki.model;

import java.io.Serializable;

public class FavVO implements Serializable{
	private int favSubjectId;
	private int userId;
	private int subjectId;
	private String subjectName;
	private String iconName;
	private String professor;
	public int getFavSubjectId() {
		return favSubjectId;
	}
	public void setFavSubjectId(int favSubjectId) {
		this.favSubjectId = favSubjectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
}
