package com.example.vo; // 20240306 Day11

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private int no;
	private String id;
	@JsonIgnore
	private String password;
	private String name;
	private String email;
	private String tel;
	private String deleted;
	private Date birth;
	private Date createdDate;
	private Date updatedDate;
}
