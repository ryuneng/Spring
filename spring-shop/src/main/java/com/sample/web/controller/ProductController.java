package com.sample.web.controller; // 20240226 Day5

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.ProductService;
import com.sample.web.form.ProductCreateForm;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired // 객체 찾아서 넣어줌 (객체 생성 X)
	private ProductService productService;

	@GetMapping(path = "/list")
	public String list() {
		
		return "product/list";    // "/WEB-INF/views/product/list.jsp"로 내부이동
	}
	
	@GetMapping("/create")
	public String form() {
		
		return "product/form";    // "/WEB-INF/views/product/form.jsp"로 내부이동
	}
	
	@PostMapping("/create") // 상품등록 폼에서 등록 버튼 눌렀을 때
	public String create(ProductCreateForm productCreateForm) { // Form 객체(Command 객체)
		productService.createProduct(productCreateForm);
		
		return "redirect:list";
	}
}
