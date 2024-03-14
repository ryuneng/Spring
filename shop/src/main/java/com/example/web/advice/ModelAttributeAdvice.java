package com.example.web.advice; // 20240311 Day14

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.service.ProductService;
import com.example.vo.Company;
import com.example.vo.ProductCategory;

import lombok.RequiredArgsConstructor;

/*
	@ModelAttribute
		- Model 객체의 속성 관련 어노테이션
		- 메서드와 매개변수에 지정할 수 있다.
		- 메서드에 @ModelAttribute 어노테이션을 지정하면 그 메서드가 반환하는 값을 Model 객체에 저장시킨다.
		- Spring mvc는 요청핸들러 메서드를 실행하기 전에 @ModelAttribute 어노테이션이 지정된 메서드를 먼저 실행해서 Model객체에 값을 저장한다.
		- 따라서, 많은 요청핸들러 메서드가 실행된 다음에 내부이동하는 JSP에서 특정한 값을 표현하는 (공통적인)부분이 있으면,
		  각각의 요청핸들러 메서드에서 그 정보를 조회해서 Model객체에 저장하지 말고,
		  @ModelAttribute 어노테이션이 지정된 메서드에서 정보를 조회하고 반환하도록 하자.
		* 해당 어노테이션을 ControllerAdvice에 등록해놓으면 모든 컨트롤러에서 사용 가능하고, Controller에서 정의하면 해당 컨트롤러에서만 사용 가능하다.
 */

@ControllerAdvice
@RequiredArgsConstructor
public class ModelAttributeAdvice {

	private final ProductService productService;
	
	// 해당 메서드가 반환하는 값을 Model 객체에 저장시키는데, 그 이름이 name = "productCategories" (중간단계 없이 jsp에서 이 이름으로 바로 호출 가능)
	@ModelAttribute(name = "productCategories")
	public List<ProductCategory> productCategories() {
		return productService.getAllProductCategories();
	}
	
	@ModelAttribute(name = "companies")
	public List<Company> companies() {
		return productService.getAllCompanies();
	}
}
