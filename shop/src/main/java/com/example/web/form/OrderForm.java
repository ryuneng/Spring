package com.example.web.form; // 20240311 Day14

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderForm {

	// 1단계에서 입력되는 값(orderform.jsp)
	private int productNo;
	private String name;
	private int price;
	private int amount;
	private int totalPrice;
	
	// 2단계에서 입력되는 값(payform.jsp)
	private String payType;
	private String cardno;
	private int months;
	private int payAmount;
}
