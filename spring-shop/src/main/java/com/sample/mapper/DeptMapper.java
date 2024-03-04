package com.sample.mapper; // 20240304 Day9 실습

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vo.Dept;

@Mapper
public interface DeptMapper {

	List<Dept> getAllDepts();

	void insertDept(Dept dept);
}
