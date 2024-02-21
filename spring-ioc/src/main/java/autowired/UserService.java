package autowired; // 20240220 Day1

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	// 1. 조립하고 싶은 Dao 타입 지정 및 멤버변수 선언
	@Autowired
	private UserDao userDao;
	
	// 2. 조립할 준비 (Setter 메소드 생성)
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 3. 사용할 메소드 정의
	public void 회원가입() {
		System.out.println("조립된 객체 : " + userDao);
		System.out.println();
		
		userDao.getUser();
		userDao.insertUser();
	}
}
