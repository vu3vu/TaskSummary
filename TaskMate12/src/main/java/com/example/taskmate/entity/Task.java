package com.example.taskmate.entity;

import java.sql.Date;

import lombok.Data;

@Data //privateでgetter setterを使うため
public class Task {

	//Table t_taskのフィールドを定義
	private Integer taskId;
	private String taskName;
	private Date limitDate;
	private String statusCode; //Status型へ変更
	private String remarks;
}
