package autowired; // 20240220 Day1

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class UserJdbcDao implements UserDao {

	@Override
	public void insertUser() {
		System.out.println("jdbc 기술로 사용자 지정하기");
	}
	
	@Override
	public void updateUser() {
		System.out.println("jdbc 기술로 사용자 수정하기");
	}
	
	@Override
	public void getUser() {
		System.out.println("jdbc 기술로 사용자 조회하기");
	}
}
