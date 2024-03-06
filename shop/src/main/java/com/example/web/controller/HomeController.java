package com.example.web.controller; // 20240305 Day10

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.AlreadyUsedEmailException;
import com.example.exception.AlreadyUsedIdException;
import com.example.service.UserService;
import com.example.web.form.UserRegisterForm;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor // = @Autowired
public class HomeController {
	
	private final UserService userService;
	
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
	public String register(@Valid UserRegisterForm form, BindingResult errors) { // @Valid: 폼클래스 안에 있는 값을 자동으로 체크해줌, BindingResult: 검사결과(위반내용)
		
		// 폼 입력값 유효성 체크를 통과하지 못한 경우, 회원가입화면으로 내부이동시킨다.
		if (errors.hasErrors()) {
			return "form";
		}
		
		try {
			// 폼 입력값 유효성 체크를 통과한 경우
			userService.registerUser(form);
		} catch (AlreadyUsedIdException ex) {
			// 이미 사용중인 아이디인 경우, 유효성 체크를 통과하지 못한 것으로 간주한다.
			// rejectValue(필드명, 에러코드, 에러메시지) 메서드는 BindingResult객체에 FieldError를 추가한다.
			// 입력폼 화면으로 내부이동 시킨다.
			errors.rejectValue("id", null, ex.getMessage());
			return "form";
			
		} catch (AlreadyUsedEmailException ex) {
			errors.rejectValue("email", null, ex.getMessage());
			return "form";
		}
			
		return "redirect:/";
	}
}
