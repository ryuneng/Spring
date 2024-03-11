package com.example.mapper; // 20240311 Day14

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Product;

@Mapper
public interface ProductMapper {

	List<Product> getProductsByCategoryNo(int categoryNo);
	Product getProductByNo(int no);
}
