package com.sample.service; // 20240304 Day9 실습

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.DeptMapper;
import com.sample.mapper.EmpMapper;
import com.sample.vo.Dept;
import com.sample.vo.Employee;
import com.sample.web.form.DeptCreateForm;
import com.sample.web.form.EmpCreateForm;

@Service
public class HrService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpMapper empMapper;

	// 부서 전체목록 조회
	public List<Dept> getAllDepts() {
		List<Dept> deptList = deptMapper.getAllDepts();
		
		return deptList;
	}

	// 부서 등록
	public void createDept(DeptCreateForm form) {
		Dept dept = Dept.builder()
				.name(form.getName())
				.tel(form.getTel())
				.fax(form.getFax())
				.build();
		
		deptMapper.insertDept(dept);
	}
	
	// 직원 전체목록 조회
	public List<Employee> getAllEmps() {
		List<Employee> empList = empMapper.getAllEmps();
		
		return empList;
	}

	// 직원 등록
	public void createEmp(EmpCreateForm form) {
		// 등록 폼에서 부서번호 조회하기 위해 필요
		Dept dept = Dept.builder()
				.no(form.getDeptNo())
				.build();
		
		// 빌더가 모든 생성자를 알아서 만들어줌
		Employee emp = Employee.builder()
				.name(form.getName())
				.tel(form.getTel())
				.email(form.getEmail())
				.salary(form.getSalary())
				.hireDate(form.getHireDate())
				.dept(dept)
				.build();
		
		empMapper.insertEmp(emp);
	}
	
	// 직원 상세정보 조회
	public Employee getEmpDetail(int no) {
		return empMapper.getEmpByNo(no);
	}

	// 직원정보 수정
	public void updateEmp(int no, EmpCreateForm form) {
		Dept dept = Dept.builder()
				.no(form.getDeptNo())
				.build();
		
		Employee emp = Employee.builder()
				.no(no)
				.name(form.getName())
				.tel(form.getTel())
				.email(form.getEmail())
				.salary(form.getSalary())
				.hireDate(form.getHireDate())
				.dept(dept)
				.build();
		
		empMapper.updateEmp(emp);
	}

	// 직원정보 삭제
	public void deleteEmp(int no, EmpCreateForm form) {
		Dept dept = Dept.builder()
				.no(form.getDeptNo())
				.build();
		
		Employee emp = Employee.builder()
				.no(no)
				.name(form.getName())
				.tel(form.getTel())
				.email(form.getEmail())
				.salary(form.getSalary())
				.hireDate(form.getHireDate())
				.dept(dept)
				.build();
		
		empMapper.deleteEmp(emp);
	}
	
}
