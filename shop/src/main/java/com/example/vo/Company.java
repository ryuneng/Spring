package com.example.vo; // 20240311 Day14

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자 추가
public class Company {

	private int no;
	private String name;
	private String tel;
	private String zipcode;
	private String address1;
	private String address2;
	
	public Company(int no) {
		this.no = no;
	}
}
