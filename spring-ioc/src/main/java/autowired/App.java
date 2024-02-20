package autowired; // 20240220 Day1

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		// ClassPathXmlApplicationContext() : 스프링 컨테이너에게 context-autowired.xml라는 환경설정파일을 알려줌
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context-autowired.xml");
		
		// getBean : 객체 꺼내옴
		UserDao userDao = ctx.getBean(UserDao.class);
		UserService userService = ctx.getBean(UserService.class);
		
		System.out.println(userDao);
		System.out.println(userService);
		
		userService.회원가입();
	}
}
