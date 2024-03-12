package com.example.web.controller; // 20240311 Day14

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	    	3. 요청핸들러 메서드의 매개변수 orderForm에 OrderForm객체를 전달한다.
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
	    	3. 요청핸들러 메서드의 매개변수로 OrderForm객체를 전달한다.
	    	4. OrderService객체의 order(OrderForm orderForm)를 실행해서 주문관련 업무로직을 실행시킨다.
	    	5. 주문정보를 확인할 수 있는 URL을 재요청 URL로 보낸다.
	 */
	@PostMapping("/step3")
	public String step3(@ModelAttribute("orderForm") OrderForm orderForm, Principal principal) { // principal : 로그인 정보
		// 주문관련 업무로직 메서드를 실행한다.
		String userId = principal.getName(); // principal.getName() : 사용자ID
		Order order = orderService.saveOrder(orderForm, userId);
		
		return "redirect:completed?orderNo=" + order.getNo();
	}
	
	@GetMapping("/completed")
	public String completed(int orderNo, Principal principal, Model model) {
		String userId = principal.getName();
		OrderDetailDto dto = orderService.getOrderDetail(orderNo, userId);
		model.addAttribute("dto", dto);
		
		return "order/completed";
	}
}
