package com.example.taskmate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.taskmate.entity.Status;
import com.example.taskmate.entity.TaskSummary;
import com.example.taskmate.form.TaskSearchListForm;
import com.example.taskmate.service.StatusService;
import com.example.taskmate.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskSearchController {

	//利用するserviceをDIする
	private final TaskService taskService;
	
	//まずDI
	private final StatusService statusService;
	
	
	//最初のリクエスト
	@GetMapping("/top")
	private String showListSelection(
			@Validated @ModelAttribute TaskSearchListForm form,
			BindingResult result,
			Model model){
		
		System.out.println("--searchList---");
		System.out.println(form);
		
		//ステータスリストをモデルに格納
		//taskName limitDateは一つの値だが、statusCodeはselectで複数選ぶためfindAllで全部リストでもってくる
		List<Status> list = statusService.findAll();
		model.addAttribute("statusList",list);//ここが肝。statusListとしてテンプレートに渡す
		
		
		return "task-list";//task-list.html というテンプレート名でレスポンスを返す
	}
	
	@PostMapping("task-search-list")
	private String searchList(Model model,
			@Validated @ModelAttribute TaskSearchListForm form,
			BindingResult result) {
	
		System.out.println("--searchList---");
		System.out.println(form);
	/*	//仮データを作成
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
		*/
		
		//mybatisを通じてDBアクセスを実施する 一覧の全件検索をする
		List<TaskSummary> list = taskService.findListAll();//DIしたサービスを呼び出し
		model.addAttribute("taskSummaryList",list);
		
		//ステータスリストをmodelに設定
		List<Status> statusList = statusService.findAll();
	    model.addAttribute("statusList",statusList);
		
		return "task-list";//task-list.html というテンプレート名でレスポンスを返す
	}
	
	
}
