package com.sample.vo; // 20240226 Day5

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	private int no;
	private String name;
	private String description;
	private int stock;
	private String status;
	private int price;
	private String filename;
	private Date updatedDate;
	private Date createdDate;
	private Company company;
	
	@Builder
	public Product(int no, String name, String description, int stock, String status, int price, String filename,
			Date updatedDate, Date createdDate, Company company) {
		super();
		this.no = no;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.status = status;
		this.price = price;
		this.filename = filename;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
		this.company = company;
	}
	
	// 아래 메서드를 통해 jsp에서 status를 원하는 형식으로 텍스트화해서 StatusText로 호출 가능
	public String getStatusText() {
		if ("SELL".equals(status)) {
			return "판매중";
		} else if ("SOLD_OUT".equals(status)) {
			return "품절";
		}
		return null;
	}
}
