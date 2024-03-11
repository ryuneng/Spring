package com.example.web.controller; // 20240311 Day14

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.service.ProductService;
import com.example.vo.Product;
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
public class OrderController {
	
	private final ProductService productService;

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
		Product product = productService.getProduct(productNo);
		model.addAttribute("product", product);
		model.addAttribute("orderForm", new OrderForm());
		
		return "order/orderform";
	}
	
	@PostMapping("/step2")
	public String step2(OrderForm orderForm) {
		System.out.println("----------------------- step2 OrderForm");
		System.out.println(orderForm);
		
		return "order/payform";
	}
	
	@PostMapping("/step3")
	public String step3(OrderForm orderForm) {
		System.out.println("----------------------- step3 OrderForm");
		System.out.println(orderForm);
		
		return "order/completed";
	}
}
