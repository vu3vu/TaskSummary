package com.example.taskmate.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class TaskSummary {
	//DBのカラムを定義
	//足りないインポートは　ctl + shift + o 
	private Integer taskId;
	private String taskName;
	private Date limitDate;
	private String statusCode;
	
	
}
