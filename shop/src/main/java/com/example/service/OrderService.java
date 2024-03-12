package com.example.service; // 20240312 Day15

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.ShopException;
import com.example.mapper.OrderItemMapper;
import com.example.mapper.OrderMapper;
import com.example.mapper.OrderPaymentMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.UserMapper;
import com.example.vo.Order;
import com.example.vo.OrderItem;
import com.example.vo.OrderPayment;
import com.example.vo.Product;
import com.example.vo.User;
import com.example.web.dto.OrderDetailDto;
import com.example.web.form.OrderForm;

import lombok.RequiredArgsConstructor;

/*
    @Transactional
    	- 선언적 트랜잭션처리를 지원하는 어노테이션(보통 Service에 부착)
    	- @Transactional 어노테이션은 인터페이스, 클래스, 메소드에 붙일 수 있다.
    	    - 인터페이스에 지정하면 해당 인터페이스를 구현한 구현객체의 각 메소드가 실행될 때마다 트랜잭션처리가 지원된다.
    	    - 클래스에 지정하면 해당 클래스로 생성한 객체의 각 메소드가 실행될 때마다 트랜잭션처리가 지원된다.
    	    - 메소드에 지정하면 해당 메소드가 실행될 때마다 트랜잭션처리가 지원된다.
    	- 선언적 트랜잭션 처리가 지원되면 해당 메소드가 실행되기 전에 새로운 트랜잭션이 시작된다.
    		- 트랜잭션이 시작된다는 말은, 데이터베이스에서 지금부터 실행하는 모든 SQL 구문의 하나의 논리적인 그룹으로 묶을 준비가 되었다는 것이다.
    		- 메소드 내에서 데이터베이스 액세스 작업을 할 때마다 해당 SQL작업을 트랜잭션에 추가한다.
    	- 선언적 트랜잭션 처리가 지원되면 해당 메소드가 종료될 때 트랜잭션을 종료한다.
    		- 해당 메소드를 실행하면서 RuntimeException의 하위 예외가 발생하면 해당 트랜잭션에 추가된 모든 작업에 대해서 rollback을 실행한다.(Exception의 하위는 안됨)
    		- 해당 메소드를 실행하면서 예외가 발생하지 않으면 해당 트랜잭션에 추가된 모든 작업에 대해서 commit을 실행한다.
 */

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final ProductMapper productMapper;
	private final OrderMapper orderMapper;
	private final OrderItemMapper orderItemMapper;
	private final OrderPaymentMapper orderPaymentMapper;
	private final UserMapper userMapper;
	
	public Order saveOrder(OrderForm orderForm, String userId) {
		// * 다른 테이블의 데이터를 insert해야 할 때는 getter를 활용해서 필요한 값을 유연하게 획득할 줄 알아야 함. !!
		// 1. 주문 정보를 저장한다.    (Order 테이블 : 누가, 언제 주문했는지)
		User user = userMapper.getUserById(userId); // DB에서는 userId가 아닌 userNo로 조회가 가능하기 때문에, 우선 userId로 유저 객체 먼저 획득하기
		
		Order order = new Order();
		order.setUser(user);						// 위에서 획득한 유저 객체를 order에 저장하기
		order.setTotalPrice(orderForm.getTotalPrice());
		
		orderMapper.insertOrder(order);
		
		// 2. 주문 상품정보를 저장한다. (OrderItem 테이블 : 무엇을 주문했는지)
		Product product = productMapper.getProductByNo(orderForm.getProductNo());
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderNo(order.getNo());
		orderItem.setAmount(orderForm.getAmount());
		orderItem.setPrice(product.getPrice());
		orderItem.setProduct(product);
		
		orderItemMapper.insertOrderItem(orderItem);
		
		// 3. 결제 정보를 저장한다.
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrder(order);
		orderPayment.setType(orderForm.getPayType());
		orderPayment.setAccNo(orderForm.getCardno());
		orderPayment.setMonths(orderForm.getMonths());
		orderPayment.setAmount(orderForm.getTotalPrice());
		
		orderPaymentMapper.insertPayment(orderPayment);
		
		return order; // step3에서 주문번호가 필요하고, Order 안에 주문번호가 있기 때문에 order를 반환해줘야 함
	}
	
	public OrderDetailDto getOrderDetail(int orderNo, String userId) {
		// dto 객체 생성(주문상세정보에 표현할 모든 값들을 한군데 모으기 위해 dto로)
		OrderDetailDto dto = new OrderDetailDto();
		
		User user = userMapper.getUserById(userId);
		Order order = orderMapper.getOrderByNo(orderNo);
		if(user.getNo() != order.getUser().getNo()) {
			throw new ShopException("다른 사용자의 주문정보는 조회할 수 없습니다.");
		}
		
		List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderNo(orderNo);
		OrderPayment payment = orderPaymentMapper.getOrderPaymentByOrderNo(orderNo);
		
		dto.setOrder(order);
		dto.setOrderItems(orderItems);
		dto.setPayment(payment);
		
		return dto;
	}
}
