package ioc; // 20240220 Day1

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		// ClassPathXmlApplicationContext() : 스프링 컨테이너에게 context.xml라는 환경설정파일을 알려줌
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		// getBean : 객체 꺼내옴
		// ProductService.class : 객체의 설계도 정보를 제공하는 리터럴
		ProductService productService = ctx.getBean(ProductService.class);
		
		productService.신규상품등록();
		productService.상품상세정보조회();
		productService.상품정보수정();
	}
}
