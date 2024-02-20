package ioc; // 20240220 Day1

/**
 * 애플리케이션 실행에 필요한 객체를 생성한다.
 * 객체들 간의 의존관계에 맞게 객체를 조립한다.
 * @author ryuneng
 *
 */
public class Container {

	public ProductService getProductService() {
		// 1. 애플리케이션 실행에 필요한 객체 생성하기
		ProductJdbcDao productJdbcDao = new ProductJdbcDao();
		ProductMybatisDao productMybatisDao = new ProductMybatisDao();
		
		ProductService productService = new ProductService();
		
		// 2. 객체들 간의 의존관계에 맞게 객체 조립하기 -> 의존성 주입 발생
		//  - setDao로 주소값을 Service에게 전달
		//  - SetDao에 JdbcDao/MybatisDao 중 사용할 객체를 지정하기만 하면
		//    Service는 단 한 줄의 코드도 변경하지 않고 사용할 객체를 변경할 수 있다. 이 작업을 Spring이 해줌
		productService.setDao(productMybatisDao);
		
		// 3. 생성, 조립된 객체 제공하기
		return productService;
	}
}
