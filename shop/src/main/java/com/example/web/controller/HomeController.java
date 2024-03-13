package com.example.web.controller; // 20240305 Day10

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.exception.AlreadyUsedEmailException;
import com.example.exception.AlreadyUsedIdException;
import com.example.service.UserService;
import com.example.vo.Company;
import com.example.vo.User;
import com.example.web.form.UserRegisterForm;

import java.util.List;

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
	
	@GetMapping("/app1")
	@ResponseBody
	public String app1() {
		return "텍스트입니다.";
	}
	
	@GetMapping("/app2")
	@ResponseBody
	public String[] app2() {
		String[] userNames = {"홍길동", "강감찬", "류관순"};
		
		return userNames;
	}
	
	@GetMapping("/app3")
	@ResponseBody
	public List<Company> app3() {
		Company c1 = new Company();
		c1.setNo(100);
		c1.setName("삼성전자");
		c1.setTel("02-1234-5678");
		
		Company c2 = new Company();
		c2.setNo(100);
		c2.setName("삼성전자");
		c2.setTel("02-1234-5678");
		
		return List.of(c1, c2);
	}
	
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
	@PostMapping("/register")// @Valid: 폼클래스 안에 있는 값을 자동으로 체크해줌, BindingResult: 검사결과(위반내용)
	public String register(@Valid UserRegisterForm form, BindingResult errors, RedirectAttributes redirectAttributes) {
		
		// 폼 입력값 유효성 체크를 통과하지 못한 경우, 회원가입화면으로 내부이동시킨다.
		if (errors.hasErrors()) {
			return "form";
		}
		
		try {
			// 폼 입력값 유효성 체크를 통과한 경우
			User user = userService.registerUser(form);
			redirectAttributes.addFlashAttribute("user", user); // 다음 요청한 jsp페이지에서 값을 뿌릴 수 있음
			
			return "redirect:/completed";
			
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
			
	}
	
	@GetMapping("/completed")
	public String completed() {
		return "completed";
	}
	
	/*
	    요청방식
	    	GET
	    요청URL
	    	localhost/login
	    요청내용
	    	로그인폼 화면을 요청한다.
	    처리내용
	    	뷰페이지(로그인 폼 화면, loginform.jsp) 이름을 반환한다.
	 */
	@GetMapping("/login")
	public String loginform() {
		return "loginform";
	}
	
	// 관리자 아닌 유저가 관리자 페이지 진입하려고 할 때 오류
	@GetMapping("/accessdenied")
	public String accessDenied() {
		return "error/denied";
	}
}
