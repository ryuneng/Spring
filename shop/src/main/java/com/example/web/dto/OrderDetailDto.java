package com.example.web.dto; // 20240311 Day14

import java.util.List;

import com.example.vo.Order;
import com.example.vo.OrderItem;
import com.example.vo.OrderPayment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailDto {

	private Order order;
	private List<OrderItem> orderItems;
	private OrderPayment payment;
	
	public int getTotalItemCount() {
	    int count = 0;
	    for (OrderItem item : orderItems) {
	        count += item.getAmount();
	    }
	    return count;
	}
	   
	public String getDescription() {
	   return orderItems.get(0).getProduct().getName() + " 외 " + (getTotalItemCount() - orderItems.get(0).getAmount()) + "개";
	}
}
/*
	* dto 생성 목적 : 주문상세정보에 표현할 모든 값들을 한군데 모음.
	               Service에서 아래처럼 사용하고, jsp에서는 ${dto.order.no}, ${dto.payment.no} 같은 형식으로 출력
	
	public OrderDetailDto getOrderDetail(int orderNo) {
		OrderDetailDto dto = new OrderDetailDto();
		
		return dto;
	}
*/