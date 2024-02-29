package com.sample.service; // 20240229 Day8

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.CompanyMapper;
import com.sample.vo.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	public List<Company> getAllCompanies() {
		return companyMapper.getAllCompanies();
	}
}
