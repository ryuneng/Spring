package com.sample.service; // 20240221 Day2

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.UserDao;
import com.sample.vo.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void register(String id, String password, String name) {
<<<<<<< HEAD
		System.out.println("회원가입 하기");
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		
		userDao.insertUser(user);
	}
	
	public User getUser(String id) {
		System.out.println("회원정보 조회하기");
=======
		User user = new User();
		user.setId(id);;
		user.setPassword(password);
		user.setName(name);
		
		userDao.insertUser(user);
	}
	
	public User getUser(String id) {
>>>>>>> refs/remotes/origin/main
		User user = userDao.getUserById(id);
		if (user == null) {
			throw new IllegalArgumentException("["+id+"] 아이디에 해당하는 사용자 정보가 없습니다.");
		}
		return user;
	}
}
