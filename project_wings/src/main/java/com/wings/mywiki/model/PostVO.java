package com.wings.mywiki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostVO {
	private int post_id;
	private int user_id,subject_id;		// ¿Ü·¡Å°
	private String title,text,create_date,update_date,delete_date;
}
