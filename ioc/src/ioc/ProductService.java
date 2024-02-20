package ioc; // 20240220 Day1

/**
 * ProductService는 상품정보를 등록, 조회, 수정한다.
 * ProductService는 상품정보를 등록, 조회, 수정하기 위해서 ProductDao 인터페이스를 구현한 객체가 필요하다.
 * @author ryuneng
 *
 */
public class ProductService {

	// 객체를 생성(결정)하지 않고, 의존성 주입을 받기 위해서 연결할 변수만 선언해놓음
	// ex) 노트북에 HDMI, USB 등을 연결하기 위해 구멍(=타입)만 뚫어놓음

	/*
	   의존성 주입을 받기 위해 해야 할 작업
	   1. 구멍(타입) 뚫기 = 멤버변수(타입=ProductDao) 선언
	   2. Setter 메소드 생성
	*/
	/*
	   클래스가 클래스를 의존하는 경우 : 결합이 높다.
	   클래스가 인터페이스를 의존하는 경우 : 결합이 낮다.
	 */
	private ProductDao dao;
	
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	
	public void 신규상품등록() {
		dao.insertProduct();
	}
	
	public void 상품상세정보조회() {
		dao.getProduct();
	}
	
	public void 상품정보수정() {
		dao.updateProduct();
	}
}
