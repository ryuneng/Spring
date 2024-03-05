package com.example.web.controller; // 20240305 Day10

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.web.form.UserRegisterForm;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Controller
/*
    @Log4j2
   		- 로그출력을 지원하는 어노테이션
   		- 클래스에 아래의 코드를 추가한다.
   			private static final Logger log = LogManager.getLogger(HomeController.class);
   		- 요청핸들러 메서드에서는 아래의 메서드를 이용해서 로그를 출력할 수 있다.
   			log.debug(메시지);
   			log.info(메시지);
   			log.error(메시지);
   		- System.out.println() 대신 반드시 log를 사용하자.
 */
@Log4j2
public class HomeController {
	
	// 홈으로 이동
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	// 회원가입 form으로 이동
	@GetMapping("/register")
	public String form(Model model) {
		model.addAttribute("userRegisterForm", new UserRegisterForm()); // 폼 입력값을 담을 수 있는 객체를 먼저 만들어야 함
		return "form";
	}
	
	// 회원가입 처리
	@PostMapping("/register")
	public String register(@Valid UserRegisterForm form, BindingResult errors) { // @Valid: 폼클래스 안에 있는 값을 자동으로 체크해줌, BindingResult: 검사결과
		
		// 폼 입력값 유효성 체크를 통과하지 못한 경우, 회원가입화면으로 내부이동시킨다.
		if (errors.hasErrors()) {
			return "form";
		}
		return "redirect:/";
	}
}
