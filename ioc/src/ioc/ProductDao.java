package ioc; // 20240220 Day1

/**
 * 상품정보 저장, 조회, 변경 작업에 대한 표준을 정의한다.
 * @author ryuneng
 *
 */
public interface ProductDao { // 확장성을 위해서는 인터페이스 필수

	void insertProduct();
	void getProduct();
	void updateProduct();
}
