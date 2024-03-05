package com.sample.service; // 20240304 Day9 실습

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.DeptMapper;
import com.sample.mapper.EmpMapper;
import com.sample.vo.Dept;
import com.sample.vo.Employee;
import com.sample.web.dto.Criteria;
import com.sample.web.dto.ListDto;
import com.sample.web.dto.Pagination;
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
		// 빌더가 모든 생성자를 알아서 만들어줌
		Dept dept = Dept.builder()
				.name(form.getName())
				.tel(form.getTel())
				.fax(form.getFax())
				.build();
		
		deptMapper.insertDept(dept);
	}
	
	// 직원 전체목록 조회
	public ListDto<Employee> getAllEmps(Criteria criteria) {
		int totalRows = empMapper.getTotalRows(criteria);
		
		Pagination pagination = new Pagination(criteria.getPage(), totalRows, criteria.getRows());
		criteria.setBegin(pagination.getBegin());
		criteria.setEnd(pagination.getEnd());
		
		List<Employee> empList = empMapper.getAllEmps(criteria);
		
		ListDto<Employee> dto = new ListDto<Employee>(empList, pagination);
		
		return dto;
	}

	// 직원 등록
	public void createEmp(EmpCreateForm form) {
//		Dept dept = Dept.builder()
//				.no(form.getDeptNo())
//				.build();
		
//		Employee emp = Employee.builder()
//				.name(form.getName())
//				.tel(form.getTel())
//				.email(form.getEmail())
//				.salary(form.getSalary())
//				.hireDate(form.getHireDate())
//				.dept(dept)
//				.build();
		
		// Form클래스에 emp를 set해주는 toEmp() 메서드 만들어두면
		// 위의 긴 코드를 아래처럼 간단하게 단 한 줄로 작성 가능
		Employee emp = form.toEmp();
		
		empMapper.insertEmp(emp);
	}
	
	// 직원 상세정보 조회
	public Employee getEmpDetail(int no) {
		return empMapper.getEmpByNo(no);
	}

	// 직원정보 수정
	public void updateEmp(int no, EmpCreateForm form) {
		// 수정 폼에서 부서번호 조회하기 위해 필요
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
	public void deleteEmp(int no) {
		
		empMapper.deleteEmp(no);
	}
	
}
