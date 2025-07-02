package com.example.taskmate.form;

import java.sql.Date;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRegistForm {
	
	@Size(min=1,max=32,message="1-32文字で入力")
	private String taskName;
	
	private Date limitDate;
	
	@Size(min=2,max=2,message="指定に誤り")
	private String statusCode;
	
	@Size(max=64,message="64文字以内")
	private String remarks;
}
