package com.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.vo.Employee;
import com.sample.web.dto.Criteria;

@Mapper
public interface EmpMapper {

	int getTotalRows(Criteria criteria); // 페이징처리 행
	List<Employee> getAllEmps(Criteria criteria); // 직원목록 조회
	
	void insertEmp(Employee emp); // 직원등록
	Employee getEmpByNo(int no);  // 직원 개별조회
	void updateEmp(Employee emp); // 직원 정보 수정
	void deleteEmp(int no); // 직원 정보 삭제
}
