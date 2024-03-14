package com.example.web.form; // 20240314 Day17

import com.example.vo.Company;
import com.example.vo.Product;
import com.example.vo.ProductCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	private int categoryNo;
	private String name;
	private int companyNo;
	private int price;
	private int amount;
	private String description;
	
	// ProductForm을 Product로 옮겨담을 때 사용할 메소드
	public Product toProduct() {
		Product product = new Product();
		product.setCategory(new ProductCategory(categoryNo));
		product.setCompany(new Company(companyNo));
		product.setName(name);
		product.setPrice(price);
		product.setStock(amount);
		product.setDescription(description);
		
		return product;
	}
}
