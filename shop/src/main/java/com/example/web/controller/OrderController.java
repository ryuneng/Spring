package com.example.web.controller; // 20240311 Day14

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.vo.Order;
import com.example.vo.Product;
import com.example.web.dto.OrderDetailDto;
import com.example.web.form.OrderForm;

import lombok.RequiredArgsConstructor;

/*
    @SessionAttributes({"orderForm"})
        - 여러 단계에 걸쳐서 입력된 데이터를 Session에 옮겨담아 로그아웃할 때까지 데이터가 유지되도록 해주는 어노테이션
        - 해당 Controller 안에서 orderForm이라는 이름으로 Model에 담기는 게 있으면 Session의 속성으로 옮겨담음
        - 사용법
			1. 하나의 Form클래스를 크게 만들고 1단계, 2단계, 3단계로 나눈다.
			2. 입력단계로 진입하는 곳에서 미리 그 객체를 만들어서 Model에 담는다. (우리는 @GetMapping("/step1")에서 했음)
			
	SessionStatus
		- HttpSession에 속성으로 추가된 객체를 삭제하는 기능을 제공한다.
		- @SessionAttributes 어노테이션으로 HttpSession에 저장된 데이터를 정리한다.
		
	RedirectAttributes
		- 요청핸들러 메소드 실행 후 redirect 방식으로 재요청하게 되는 다음 요청핸들러 메소드에 전달할 정보를 저장할 수 있는 객체
		- 요청핸들러 메소드에서 저장/변경/삭제 작업을 수행한 후에는 재요청 URL을 응답으로 보내게 되는 경우,
		  응답이 완료됨과 동시에 요청객체와 응답객체가 사라지기 때문에 Model에 데이터를 담아놓으면 전부 사라진다.
		  일반적으로 재요청 URL을 응답으로 보내는 요청핸들러 메소드에서는 뷰에 데이터를 전달할 방법이 없다.
		- 주요 메소드
		    1. RedirectAttributes addAttribute(String name, Object value)
		    	- 요청핸들러 메소드가 반환하는 재요청 URL에 쿼리스트링으로 추가될 값을 저장한다.
		    	  * 주로 복잡한 요청 URL일 때 사용
		    	- 예시)
		    		public String sample1(Form form, RedirectAttributes redirectAttributes) {
		    			redirectAttributes.addAttribute("page", 1);
		    			redirectAttributes.addAttribute("sort", "date");
		    			redirectAttributes.addAttribute("rows", 10);
		    			
		    			return "redirect:list";
		    		}
		    		* 재요청 URL : list?page=1&sort=date&rows=10
		    
		    		public String sample1(Form form, RedirectAttributes redirectAttributes) {
		    			redirectAttributes.addAttribute("page", 1);
		    			redirectAttributes.addAttribute("sort", "date");
		    			redirectAttributes.addAttribute("rows", 10);
		    			
		    			return "redirect:list/{page}";
		    		}
					* 재요청 URL : list/1?sort=date&rows=10
					* {속성명} 표현식은 해당 위치에 RedirectAttributes에 추가한 속성값이 표시되고, 나머지는 쿼리스트링 형태로 추가된다.
		    
		    2. RedirectAttributes addFlashAttribute(String name, Object value)
		    	- 이 메소드로 추가된 정보는 재요청 URL로 새로 요청한 요청핸들러 메소드의 뷰에 값을 전달할 수 있다.
		    	- 이 메소드로 추가된 정보는 일시적으로 HttpSession객체에 속성으로 저장된다.
		    	- 재요청 URL로 새로 요청한 요청핸들러 메소드의 Model로 값이 추가되고, 세션에서는 삭제된다.
 */

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@SessionAttributes({"orderForm"})
@PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
public class OrderController {
	
	private final ProductService productService;
	private final OrderService orderService;

	/*
	    요청 방식
	    	GET
	    요청 URL
	    	localhost/order/step1?no=172
	    요청 파라미터
	    	no=172
	    요청 내용
	    	지정된 상품번호에 대한 주문정보 입력폼을 요청한다.
	    처리 내용
	    	요청파라미터로 전달받은 상품정보에 대한 상세정보를 조회해서 모델에 저장하고, 주문정보 입력화면으로 내부이동한다.
	 */
	@GetMapping("/step1")
	public String step1(@RequestParam("no") int productNo, Model model) {
		// 1. 주문폼 화면에 출력할 상품정보를 조회하고, Model객체에 저장한다.
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		
		// 2. 여러 단계로 구분된 입력작업에서 입력되는 데이터를 저장할 OrderForm객체를 생성하고, Model객체에 저장한다.
		//    "orderForm"이라는 이름으로 Model객체에 저장되는 데이터는 @SessionAttributes("orderForm") 설정 때문에
		//    OrderForm객체가 HttpSession에 자동으로 저장된다.
		model.addAttribute("orderForm", new OrderForm());
		
		return "order/orderform";
	}
	
	/*
	    요청 방식
	    	POST
	    요청 URL
	    	localhost/order/step2
	    요청 파라미터
	    	productNo=122
	    	&name=갤럭시플립6
	    	&price=1500000
	    	&amount=1
	    	&totalPrice=1500000
	    요청 내용
	    	1단계 입력폼에서 입력한 내용을 전달하고, 2단계 입력폼을 요청한다.
	    처리 내용
	    	1. @ModelAttribute("orderForm") 어노테이션은 HttpSession에서 "orderForm" 이름으로 저장된 OrderForm객체를 가져온다.
	    	2. 요청파라미터로 전달된 값을 분석해서 OrderForm객체에 저장한다.
	    	3. 요청핸들러 메소드의 매개변수 orderForm에 OrderForm객체를 전달한다.
	    	--- 3번까지는 Spring이 수행하고, 4~5번은 개발자가 작성
	    	4. 2단계 입력폼에 표시할 정보를 조회하고, Model에 저장한다.
	    	5. 2단계 입력폼의 뷰페이지 이름을 반환한다.
	 */
	@PostMapping("/step2")
	public String step2(@ModelAttribute("orderForm") OrderForm orderForm) {
		// 2단계 입력폼에서 필요한 정보를 조회하고, Model에 저장하는 작업을 생략한다.
		return "order/payform";
	}
	
	/*
	    요청 방식
	    	POST
	    요청 URL
	    	localhost/order/step3
	    요청 파라미터
	    	payType=card
	    	&cardno=1234-5678-1234-5678
	    	&months=3
	    	&payAmount=1500000
	    요청 내용
	    	2단계 입력폼에서 입력한 결제정보를 서버에 제출하고, 주문을 요청한다.
	    처리 내용
	    	1. @ModelAttribute("orderForm") 어노테이션은 HttpSession에서 "orderForm"이름으로 저장된 OrderForm객체를 가져온다.
	    	2. 요청파라미터로 전달된 값을 분석해서 OrderForm객체에 저장한다.(결제정보가 OrderForm객체에 저장된다.)
	    	3. 요청핸들러 메소드의 매개변수로 OrderForm객체를 전달한다.
	    	4. OrderService객체의 order(OrderForm orderForm)를 실행해서 주문관련 업무로직을 실행시킨다.
	    	5. 주문정보를 확인할 수 있는 URL을 재요청 URL로 보낸다.
	 */
	@PostMapping("/step3")											   // Principal : 로그인 정보
	public String step3(@ModelAttribute("orderForm") OrderForm orderForm, Principal principal, SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
		// 주문관련 업무로직 메소드를 실행한다.
		String userId = principal.getName(); // principal.getName() : 사용자ID
		Order order = orderService.saveOrder(orderForm, userId);
		
		sessionStatus.setComplete(); // @SessionAttributes로 HttpSession에 저장된 데이터 삭제
		
		// 쿼리스트링 추가를 위해 아래(주석)처럼 적어야 했던 걸 redirectAttributes.addAttribute로 간단하게
		// return "redirect:completed?orderNo=" + order.getNo();
		redirectAttributes.addAttribute("orderNo", order.getNo());
		
		return "redirect:completed";
	}
	
	@GetMapping("/completed")
	public String completed(int orderNo, Principal principal, Model model) {
		String userId = principal.getName();
		OrderDetailDto dto = orderService.getOrderDetail(orderNo, userId);
		model.addAttribute("dto", dto);
		
		return "order/completed";
	}
	
	/*
	    요청 방식
	    	GET
	    요청 URL
	    	localhost/order/detail/100002 (orders.jsp > a태그의 href속성값 참고)
	    요청 파라미터
	    	없음
	    	대신, 요청 경로에 주문번호가 포함되어 있다.
	 */
	@GetMapping("/detail/{orderNo}")
	public String detail(@PathVariable("orderNo") int orderNo, Principal principal, Model model) {
		String userId = principal.getName();
		OrderDetailDto dto = orderService.getOrderDetail(orderNo, userId);
		model.addAttribute("dto", dto);
		
		return "order/detail";
	}
}
