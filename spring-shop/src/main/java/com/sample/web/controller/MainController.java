package com.sample.web.controller; // 20240226 Day5

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(path = "/")
	public String home(Model model) {
		model.addAttribute("msg", "홈페이지 방문을 환영합니다.");
		return "home"; // Controller가 반환하는 건 항상 jsp 페이지의 경로 및 이름
	}				   // 원래 /WEB-INF/views/home.jsp 로 적어야 하는데, application.properties에서 미리 경로를 지정해놨기 때문에 home만 적어도 됨
}
