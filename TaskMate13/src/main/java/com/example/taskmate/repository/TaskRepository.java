package com.example.taskmate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.taskmate.entity.Task;
import com.example.taskmate.entity.TaskSummary;

@Mapper
public interface TaskRepository {

	// 一覧全件検索
	List<TaskSummary> selectListAll();
	
	//登録　（抽象メソッド）
	void insert(@Param("task") Task task);//プレースホルダ名"task"を指定
	
	//13 追加
	//一覧条件検索 引数は検索条件が格納されているTask型
	//Paramアノテーションのtaskはマッパーで使用する
	List<TaskSummary> selectListByConditions(@Param("task") Task task);
	
}