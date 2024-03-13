package com.example.web.dto; // 20240313 Day16

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto { // password는 안내려보내려고 dto 만듦

	private int no;
	private String id;
	private String name;
	private String tel;
	private String email;
	@JsonFormat(pattern = "M월 d일")
	private Date birth;
	@JsonFormat(pattern = "yyyy년 M월 d일")
	private Date createdDate;
}
