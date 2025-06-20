package com.example.taskmate.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.taskmate.entity.TaskSummary;

@Controller
public class TaskSearchController {

	//最初のリクエスト
	@GetMapping("/top")
	private String showListSelection() {
		return "task-list";//task-list.html というテンプレート名でレスポンスを返す
	}
	
	@PostMapping("task-search-list")
	private String searchList(Model model) {
		
		//仮データを作成
		List<TaskSummary> list = new ArrayList<TaskSummary>();
		TaskSummary t = new TaskSummary();
		t.setTaskId(1);
		t.setTaskName("task1");
		t.setLimitDate(Date.valueOf("2025-06-20"));
		t.setStatusCode("00");
		list.add(t);
		
		t = new TaskSummary();
		t.setTaskId(2);
		t.setTaskName("task2");
		t.setLimitDate(Date.valueOf("2025-06-21"));
		t.setStatusCode("05");
		list.add(t);
		
		//結果を格納してテンプレートでリターン
		model.addAttribute("taskSummaryList",list);
		
		return "task-list";//task-list.html というテンプレート名でレスポンスを返す
	}
	
	
}
