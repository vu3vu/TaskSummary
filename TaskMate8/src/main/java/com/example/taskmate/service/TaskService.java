package com.example.taskmate.service;

import java.util.List;

import com.example.taskmate.entity.TaskSummary;

public interface TaskService {

	//このインターフェースの役割は？なぜこんなしょうもない短いコードだけで書いているか
	
	List<TaskSummary> findListAll();
	
	
}
