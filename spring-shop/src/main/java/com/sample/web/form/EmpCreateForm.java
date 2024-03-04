package com.sample.web.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sample.vo.Dept;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpCreateForm {

	private String name;
	private String tel;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private int salary;
	private int deptNo; // form.jsp에서 <option value="${dept.no }"> 이기 때문에 타입은 객체가 아니라 int여야 함
}
