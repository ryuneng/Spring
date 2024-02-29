package com.sample.vo; // 20240229 Day8

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // vo에 기본 생성자는 반드시 있어야 함
@Getter
@Setter
@ToString
public class Company {

	private int no;
	private String name;
	private String tel;
	private String zipcode;
	private String address1;
	private String address2;
	
	@Builder
	public Company(int no, String name, String tel, String zipcode, String address1, String address2) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}
}
