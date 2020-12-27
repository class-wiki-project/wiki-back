package com.wings.mywiki.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OnlinVO implements Serializable{
	private int onlineId;
	private String keyId;
	private int userId;
}
