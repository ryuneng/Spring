package noneioc; // 20240220 Day1

public class UserService {

	// UserService는 자신이 사용할 객체(의존하는 객체)를 직접 생성(결정)했다. --> 제어의 역전이 아님.
	UserJdbcDao dao = new UserJdbcDao();
	
	
	public void 회원가입기능() {
		dao.insert();
	}
	
	public void 내정보조회기능() {
		dao.select();
	}
}
