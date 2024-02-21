package dependency; // 20240221 Day2

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너 객체 생성하기
		// ClassPathXmlApplicationContext 객체는 context-dependency.xml 설정 파일을 읽어서 객체를 생성하고 조립한다.
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context-dependency.xml");
		
		// 스프링 컨테이너가 생성하는 객체 가져오기
		Cloud cloud1 = ctx.getBean("cloud1", Cloud.class);
		Cloud cloud2 = ctx.getBean("cloud1", Cloud.class);
		Cloud cloud3 = ctx.getBean("cloud1", Cloud.class);
		Cloud cloud4 = ctx.getBean("cloud1", Cloud.class);
		Cloud cloud5 = ctx.getBean("cloud1", Cloud.class);
		Cloud cloud6 = ctx.getBean("cloud1", Cloud.class);
		
		// bean 태그의 scope 속성값이 singleton일 경우 아래 해시코드 결과 값이 모두 동일함
		// bean 태그의 scope 속성값이 prototype일 경우 아래 해시코드 결과 값이 모두 다름. 즉, 객체가 계속 새로 생성됨
		System.out.println(cloud1);
		System.out.println(cloud2);
		System.out.println(cloud3);
		System.out.println(cloud4);
		System.out.println(cloud5);
		System.out.println(cloud6);
		
		cloud1.connect();
	}
}
