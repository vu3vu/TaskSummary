package com.example.taskmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	/* 完了後のリダイレクト先（タスク更新系） */
	@GetMapping("taks-complete")
	private String completeTask() {
		return "task-complete";
	}
}
