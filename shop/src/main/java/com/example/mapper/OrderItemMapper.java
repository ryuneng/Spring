package com.example.mapper; // 20240312 Day15

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.OrderItem;

@Mapper
public interface OrderItemMapper {
	
	void insertOrderItem(OrderItem orderItem);
	List<OrderItem> getOrderItemsByOrderNo(int orderNo); // 이번 주문에 주문한 상품 조회
}
