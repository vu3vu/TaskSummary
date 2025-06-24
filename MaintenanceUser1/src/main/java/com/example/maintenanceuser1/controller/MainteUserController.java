package com.example.maintenanceuser1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainteUserController {

	@GetMapping("user-top")
	private String userTop() {
		return "user-top";
	}
	@PostMapping("search-user")
	private String searchUser(Model mv) {
		
		return "user-top";
	}
}
