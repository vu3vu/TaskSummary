package com.example.taskmate.service;

import java.util.List;

import com.example.taskmate.entity.TaskSummary;

public interface TaskService {

	List<TaskSummary> findListAll();
	
	
}
