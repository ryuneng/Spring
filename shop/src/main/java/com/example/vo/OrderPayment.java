package com.example.vo; // 20240312 Day15

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPayment {

	private int no;
	private String type;
	private String accNo;
	private int months;
	private int amount;
	private String status;
	private Date updatedDate;
	private Date createdDate;
	private Order order; // private int orderNo; 도 가능
}
