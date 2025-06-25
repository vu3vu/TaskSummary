package com.example.maintenanceuser1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainteUserController {

	@GetMapping("admin")
	private String userTop() {
		return "admin/admin-top";
	}
	@GetMapping("user-search-list")
	private String searchUser(Model mv) {
		
		return "admin/user-top";
	}
}
