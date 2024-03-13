package com.example.web.controller; // 20240308 Day13

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.UserService;
import com.example.vo.User;
import com.example.web.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')") // Admin 권한이 있는 사용자만 이 컨트롤러의 요청 핸들러메서드를 요청할 수 있음
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	
	@GetMapping("/home")
	public String home() {
		return "admin/home";
	}
	
	@GetMapping("/users")
	public String users(Model model) { // model은 jsp에 표현하려고 담음
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin/users";
	}
	
	@GetMapping("/users/{userId}") // /users : 내부페이지로 이동, /{userId} : 데이터로 내려보냄(@ResponseBody때문에)
	@ResponseBody
	public UserDto user(@PathVariable("userId") String userId) {
		User user = userService.getUser(userId);
		UserDto dto = new UserDto();
		
		BeanUtils.copyProperties(user, dto); // user에 있는 동일한 이름의 멤버변수를 dto로 전부 복사해줌(set할 필요 없음)
		
		return dto;
	}
}
