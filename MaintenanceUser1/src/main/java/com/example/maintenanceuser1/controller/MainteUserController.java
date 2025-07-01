package com.example.maintenanceuser1.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.maintenanceuser1.entity.User;
import com.example.maintenanceuser1.service.MaintenService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainteUserController {

	private final MaintenService maintenService;
	
	@GetMapping("/admin")
	public String userTop() {
		return "admin-top";
	}
	
	@PostMapping("/user-search-list")
	public String userserach() {
		return "user-top";
	}
	@PostMapping("/user-search")
	public String searchUser(Model mv) {
		//mybatisを通じてDBアクセスを実施する
		List<User> list = maintenService.findListAll();//DIしたサービスを呼び出し
		mv.addAttribute("taskSummaryList",list);
		return "user-top";
	}
}
