package com.example.web.controller; // 20240308 Day13

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.ProductService;
import com.example.service.UserService;
import com.example.vo.Product;
import com.example.vo.ProductCategory;
import com.example.vo.User;
import com.example.web.dto.UserDto;
import com.example.web.form.ProductForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN')") // Admin 권한이 있는 사용자만 이 컨트롤러의 요청 핸들러메서드를 요청할 수 있음
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	private final ProductService productService;
	
	@GetMapping("/home")
	public String home() {
		return "admin/home";
	}
	
	// 상품관리에서 상품 목록화면 요청을 처리하는 요청핸들러 메소드
	@GetMapping("/product/list")
	public String products(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "admin/product/list";
	}
	
	// 상품관리에서 상품 등록화면 요청을 처리하는 요청핸들러 메소드
	@GetMapping("/product/create")
	public String productform() {
		return "admin/product/form";
	}
	
	// 상품관리에서 상품 등록 요청을 처리하는 요청핸들러 메소드
	@PostMapping("/product/create")
	public String createProduct(ProductForm productForm) {
		productService.createProduct(productForm);
		
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("/user/list")
	public String users(Model model) { // model은 jsp에 표현하려고 담음
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin/user/list";
	}
	
	/*
	    요청 방식
	    	GET
	    요청 URL
	    	http://localhost/admin/user/hong
	    요청 파라미터
	    	없음
	    요청 내용
	    	요청 URL에 포함된 사용자 아이디에 해당하는 사용자정보를 요청한다.
	    처리 내용
	    	요청 URL에 포함된 사용자 아이디를 @PathVariable 어노테이션을 이용해서 요청핸들러 메소드의 매개변수에 바인딩시키고,
	    	해당 아이디로 사용자를 조회한 다음 UserDto객체를 생성해서 옮겨 담고, 직렬화시켜서 응답으로 보낸다.
	 */
	@GetMapping("/users/{userId}") // /users : 내부페이지로 이동, /{userId} : 경로 변수, @PathVariable가 메소드의 파라미터에 매핑할 때 사용함(중괄호 쓰는 건 규칙)
	@ResponseBody
	public UserDto user(@PathVariable("userId") String userId) {
		User user = userService.getUser(userId);
		UserDto dto = new UserDto();
		
		BeanUtils.copyProperties(user, dto); // user에 있는 동일한 이름의 멤버변수를 dto로 전부 복사해줌(set할 필요 없음)
		
		return dto;
	}
	
	// 상위카테고리 받아서 하위카테고리 반환
	@GetMapping("/category")
	@ResponseBody
	public List<ProductCategory> categories(@RequestParam("catNo") int catNo) {
		return productService.getAllSubProductCategories(catNo);
	}
	
	// 상품 상세정보 반환
	@GetMapping("/product/{no}")
	@ResponseBody
	public Product getproduct(@PathVariable("no") int productNo) {
		return productService.getProduct(productNo);
	}
	
	// 상품 수정
	@PostMapping("/product/modify")
	@ResponseBody
	public void modifyProduct(@RequestBody Product product) { // ProductForm으로 만들어도 되는데, Product로 하면 Category랑 Company를 객체로 받아올 수 있어서 예시로
		productService.updateProduct(product);
	}
}
