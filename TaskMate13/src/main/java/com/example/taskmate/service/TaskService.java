package com.example.taskmate.service;

import java.util.List;

import com.example.taskmate.entity.Task;
import com.example.taskmate.entity.TaskSummary;

public interface TaskService {

	//このインターフェースの役割は？なぜこんなしょうもない短いコードだけで書いているか
	
	List<TaskSummary> findListAll();
	
	//登録
	void regist(Task task);
	
	//13 一覧条件検索
	List<TaskSummary> findListByConditions(Task task);
}
