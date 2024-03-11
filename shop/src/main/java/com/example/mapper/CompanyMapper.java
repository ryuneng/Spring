package com.example.mapper; // 20240311 Day14

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Company;

@Mapper
public interface CompanyMapper {

	List<Company> getCompanies();
	Company getCompanyByNo(int no);
}
