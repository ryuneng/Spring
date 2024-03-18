package com.example.web.controller; // 20240318 Day19 - Thymeleaf Day1

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String form() {
		return "register-form";
	}
}
