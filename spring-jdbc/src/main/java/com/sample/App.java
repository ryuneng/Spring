package com.sample; // 20240222 Day3

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.service.UserService;
import com.sample.vo.User;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		// ConnectionPool, JdbcTemplate, UserDao, UserService 객체가 생성되었다.
		// UserService <--- UserDao <--- JdbcTemplate <--- Connection Pool 가 조립이 완료된다.
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		
		UserService userService = ctx.getBean(UserService.class);
//		userService.registerUser(new User("kang", "zxcv1234", "강감찬", "010-3850-1245", "kang@gmail.com"));
		
//		User user = userService.getUserDetail("kim2");
//		System.out.println(user.getId() + ", " + user.getName());
		
		userService.registerUser(new User("hong", "zxcv1234", "홍길동", "010-1284-4194", "hong@gmail.com"));
	}
}
