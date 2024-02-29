package com.sample.mapper; // 20240229 Day8

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vo.Company;

@Mapper
public interface CompanyMapper {

	List<Company> getAllCompanies();
}
