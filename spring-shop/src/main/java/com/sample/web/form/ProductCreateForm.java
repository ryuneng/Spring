package com.sample.web.form; // 20240226 Day5

import org.springframework.web.multipart.MultipartFile;

import com.sample.vo.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCreateForm { // Form 클래스 (폼 입력값을 담기 위한 클래스)
	// 변수명은 입력 폼(product > form.jsp)의 name과 동일하게
	private int companyNo; // form.jsp에서 <option value="${company.no }"> 이기 때문에 타입은 객체가 아니라 int여야 함
	private String name;
	private int price;
	private int stock;
	private MultipartFile photofile; // MultipartFile : 스프링에서 첨부파일 업로드를 지원하는 API
	private String description;
	
}
