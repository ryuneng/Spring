package com.sample.dao; // 20240222 Day3

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sample.vo.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate template;
	
	public void insertUser(User user) {
		String sql = "insert into sample_users values (?, ?, ?, ?, ?)";
		template.update(sql, user.getId(), user.getPassword(), user.getName(), user.getTel(), user.getEmail());
	}
	
	public void deleteUser(String id) {
		String sql = "delete from sample_users where user_id = ?";
		template.update(sql, id);
	}
	
	public void updateUser(User user) {
		String sql = "update sample_users set user_password = ?, user_tel = ?, user_email = ? where user_id = ?";
		template.update(sql, user.getPassword(), user.getTel(), user.getEmail(), user.getId());
	}
	
	public List<User> getAllUsers() {
		return null;
	}
	
	public User getUserById(String id) {
		String sql = "select * from sample_users where user_id = ?";
		/* 1. 익명객체로 람다식 없이 작성 (람다식 : 인터페이스의 구현부(매개변수와 구현코드)를 작성, 매개변수에 타입은 안적어도 됨)
		return template.queryForObject(sql, new RowMapper<User>() { // RowMapper의 구현체(인터페이스를 즉석에서 구현하는 익명객체)
			public User mapRow(ResultSet rs, int rownum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_password"));
				
				return user;
			}
		}, id); // id는 getUserById의 매개변수니까 인자값(파라미터값)으로 같이 전달해줘야 함
		 */
		
		// 2. 람다식으로 작성 (RowMapper를 구현하는 형식은 어차피 맨날 동일하니까 이렇게 작성해도 자바에서 자동으로 1번으로 변환해줌) 
		//   *람다식 사용 시 제한사항 - 구현하려는 인터페이스의 '추상메소드'는 무조건 1개만 있어야 함. 2개 이상 있으면 익명객체로 작성해야 함
		return template.queryForObject(sql, (rs, rownum) -> {
			User user = new User();
			user.setId(rs.getString("user_id"));
			user.setPassword(rs.getString("user_password"));
			user.setName(rs.getString("user_name"));
			user.setTel(rs.getString("user_tel"));
			user.setEmail(rs.getString("user_email"));
			
			return user;
		}, id);
	}
}