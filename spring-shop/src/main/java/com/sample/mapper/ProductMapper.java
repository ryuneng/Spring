package com.sample.mapper; // 20240226 Day5

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.vo.Product;
import com.sample.web.dto.Criteria;

@Mapper
public interface ProductMapper {

	int getTotalRows(Criteria criteria);
	List<Product> getProducts(Criteria criteria);
	
	void insertProduct(Product product);
	List<Product> getAllProducts(); // select 조회 결과가 행이 여러 건일 때는 무조건 List
	Product getProductByNo(int no);
}

// mybatis는 자동으로 만들어주기 때문에 약속된 룰이 있음
// 인터페이스의 메서드 이름은 xml의 id와 같아야 함. 내 마음대로 정할 수 없음