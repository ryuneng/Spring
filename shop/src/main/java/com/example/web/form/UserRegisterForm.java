package com.example.web.form; // 20240305 Day10

import java.util.Date;

import com.example.vo.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterForm {

	// vo는 테이블과 관련된 클래스라서 이런 어노테이션을 부착할 수 없기 때문에 Form클래스가 따로 존재
	@NotBlank(message = "아이디는 필수입력값입니다.") // @NotBlank : 입력값 유효성 체크
	@Size(min = 3, max = 20, message = "아이디는 최소 3글자 이상, 최대 20글자 이하만 가능합니다.") 
	private String id;
	
	@NotBlank(message = "비밀번호는 필수입력값입니다.")
	@Size(min = 8, message = "비밀번호는 최소 8글자 이상만 가능합니다.")
	private String password;
	
	@NotBlank(message = "이름은 필수입력값입니다.")
	private String name;
	
	@NotBlank(message = "이메일은 필수입력값입니다.")
	@Email(message = "유효한 형식의 이메일이 아닙니다.")
	private String email;
	
	@NotBlank(message = "전화번호는 필수입력값입니다.")
	private String tel;
	
	@NotNull(message = "생년월일은 필수입력값입니다.") // Date타입은 문자열이 아니기 때문에 NotNull
	@Past(message = "생년월일은 오늘보다 이전 날짜만 가능합니다.")
	private Date birth;
	
	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setTel(tel);
		user.setBirth(birth);
		
		return user;
	}
}
