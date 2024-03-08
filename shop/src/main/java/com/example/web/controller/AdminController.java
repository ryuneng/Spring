package com.example.web.controller; // 20240308 Day13

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')") // Admin 권한이 있는 사용자만 이 컨트롤러의 요청 핸들러메서드를 요청할 수 있음
public class AdminController {

	@GetMapping("/home")
	public String home() {
		return "admin/home";
	}
}
