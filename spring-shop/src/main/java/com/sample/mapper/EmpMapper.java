package com.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.vo.Employee;

@Mapper
public interface EmpMapper {

	List<Employee> getAllEmps();
	
	void insertEmp(Employee emp);

	Employee getEmpByNo(int no);

	void updateEmp(Employee emp);

	void deleteEmp(Employee emp);
}
