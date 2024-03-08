package com.example.vo; // 20240308 Day13

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor  // 매개변수 없는 생성자
@AllArgsConstructor // 모든 매개변수 조합 생성자
public class UserRole {

	private int userNo;
	private String rolename;
}
