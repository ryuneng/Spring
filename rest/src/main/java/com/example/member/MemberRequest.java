package com.example.member; // 20240327 Day26

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest { // form클래스같은 것, 화면 없이 데이터만 제공할 것

	@Schema(description = "이메일", example = "hong@gmail.com")
	private String email;
	@Schema(description = "비밀번호", example = "영어 대소문자 및 숫자")
	private String password;
	@Schema(description = "이름", example = "홍길동")
	private String name;
	@Schema(description = "전화번호", example = "010-1234-5678")
	private String tel;
	
	public Member toEntity() {
		Member member = new Member();
		member.setEmail(email);
		member.setPassword(password);
		member.setName(name);
		member.setTel(tel);
		member.setCreatedDate(LocalDateTime.now());
		
		return member;
	}
}
