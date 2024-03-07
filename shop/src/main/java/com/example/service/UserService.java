package com.example.service; // 20240306 Day11

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exception.AlreadyUsedEmailException;
import com.example.exception.AlreadyUsedIdException;
import com.example.mapper.UserMapper;
import com.example.vo.User;
import com.example.web.form.UserRegisterForm;

import lombok.RequiredArgsConstructor;

/*
    @RequiredArgsConstructor
    	- 초기화가 필요한 멤버변수를 매개변수로 가지는 생성자 메서드를 자동으로 추가한다.
    		* final 키워드가 지정되어 있는 멤버변수는 반드시 초기화가 필요하다.
    		* final 키워드가 지정되어 있는 멤버변수를 초기화하는 방법은 2가지가 있다.
    		    - 멤버변수에 직접 값 대입하기
    		    - 생성자 메서드의 초기화작업으로 값 대입하기
    	- 스프링에서 의존성 주입이 필요한 멤버변수에 final 키워드를 지정하고,
    	  @RequiredArgsConstructor를 클래스에 추가하면
    	  스프링 컨테이너가 자동으로 의존성을 주입한다.
 */

@Service
@RequiredArgsConstructor
public class UserService {
	// 회원가입 시 비밀번호 암호화를 위해서 PasswordEncoder 구현객체를 주입받는다.
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	// 유저 단건 조회
	public User getUser(String id) {
		
		return userMapper.getUserById(id);
	}
	
	// 회원가입 등록
	public void registerUser(UserRegisterForm form) {
		User foundUser = userMapper.getUserById(form.getId());
		if (foundUser != null) {
			throw new AlreadyUsedIdException("["+form.getId()+"]는 이미 사용중인 아이디입니다.");
		}
		
		foundUser = userMapper.getUserByEmail(form.getEmail());
		if (foundUser != null) {
			throw new AlreadyUsedEmailException("["+form.getEmail()+"]는 이미 사용중인 이메일입니다.");
		}
		
		User user = form.toUser();
		// 비밀번호 암호화
		String secretPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(secretPassword);
		
		userMapper.insertUser(user);
	}
}
