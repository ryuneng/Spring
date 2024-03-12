package com.example.vo; // 20240312 Day15

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {

	private int orderNo;
	private int amount;
	private int price; // 주문 당시의 가격(할인 등으로 인해 주문 당시의 가격과 현재 제품 가격이 달라졌을 수 있음)
	private Product product;
}
