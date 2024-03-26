package com.example.member; // 20240326 Day25

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
	@RestController
		- @Controller + @ResponseBody 가 합쳐진 어노테이션
		- 이 어노테이션이 지정된 컨트롤러의 모든 요청핸들러 메소드는
		  요청핸들러 메소드가 반환하는 값이 응답메시지의 바디부에 포함되어 클라이언트로 전달된다.
		- @ResponseBody은 요청핸들러 메소드가 반환하는 값이 View를 사용하지 않고,
		  HttpMessageConvertor를 사용해서 응답 컨텐츠로 변환된다.
		  
	ResponseEntity<T>
		- HTTP 응답을 표현하는 객체
		- HTTP 상태코드와 HTTP 응답데이터를 한번에 표현할 수 있는 객체
		- T는 ResponseEntity객체에 담기는 응답데이터의 타입이다.
 */
@RestController
@RequestMapping("/api/v1")
public class MemberController {

	@GetMapping("/members/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") Long id) {
		Member member = new Member();
		member.setId(100L);
		member.setEmail("hong@gmail.com");
		member.setPassword("zxcv1234");
		member.setName("홍길동");
		member.setTel("010-1234-5678");
		member.setCreatedDate(LocalDateTime.now());
		member.setUpdatedDate(LocalDateTime.now());
		
		return ResponseEntity.ok().body(member);
//		return ResponseEntity.ok(member);
//		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
	
		return ResponseEntity.ok().build(); // 데이터 없이 응답코드만 반환
	}
}
