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
import com.example.taskmate.entity.Task;
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

		
//		//mybatisを通じてDBアクセスを実施する 一覧の全件検索をする
//		List<TaskSummary> list = taskService.findListAll();//DIしたサービスを呼び出し
//		model.addAttribute("taskSummaryList",list);

		//13 条件検索ように改造
		// form -> entity 検索条件はTask
		Task task = new Task();
		//taskName設定
		if(!form.getTaskName().equals("")) {
			task.setTaskName("%" + form.getTaskName() + "%"); //LIKE演算子をつかうために%taskName%としている
		}
		//limitDate設定
		task.setLimitDate(form.getLimitDate());  //指定がなければnullとする
		//statuscode設定
		if(!form.getStatusCode().equals("")) { //nullだったらそれを条件で検索されないようにする
			task.setStatusCode(form.getStatusCode());
		}
		//一覧の条件検索
		List<TaskSummary> list = taskService.findListByConditions(task);
		model.addAttribute("taskSummaryList", list);
		
		//ステータスリストをmodelに設定
		List<Status> statusList = statusService.findAll();
	    model.addAttribute("statusList",statusList);
		
		return "task-list";//task-list.html というテンプレート名でレスポンスを返す
	}
	
	
}
