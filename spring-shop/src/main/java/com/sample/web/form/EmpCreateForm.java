package com.sample.web.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sample.vo.Dept;
import com.sample.vo.Employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpCreateForm {
	// Form클래스에는 단순한 타입만 가능, 객체타입은 사용안함
	private String name;
	private String tel;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private int salary;
	private int deptNo; // form.jsp에서 <option value="${dept.no }"> 이기 때문에 타입은 객체가 아니라 int여야 함
	
	// 이렇게 작성해두면 Service에서 Builder의 메서드체이닝으로 길게 작성하지 않고 이 메서드만 호출하면 됨
	public Employee toEmp() {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setTel(tel);
		emp.setEmail(email);
		emp.setSalary(salary);
		emp.setHireDate(hireDate);
		Dept dept = new Dept();
		dept.setNo(deptNo);
		emp.setDept(dept);
		
		return emp;
	}
}
