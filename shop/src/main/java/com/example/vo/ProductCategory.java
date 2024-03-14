package com.example.vo; // 20240311 Day14

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자 추가
public class ProductCategory {

	private int no;
	private String name;
	
	public ProductCategory(int no) {
		this.no = no;
	}
}
