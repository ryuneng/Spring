package com.example.user; // 20240319 Day20

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	// 테스트 케이스를 작성할 때는 반드시 void (매개변수 전달 불가)
	@Test
	@DisplayName("UserRepository 구현객체는 Null이 아니다.")
	public void testConfig() {
		assertThat(userRepository).isNotNull(); // Null이 아니라고 확신한다.
	}
	
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("hong");
		user.setPassword("zxcv1234");
		user.setName("홍길동");
		user.setEmail("hong@gmail.com");
		user.setTel("010-1234-5678");
		
		userRepository.save(user);
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
}
