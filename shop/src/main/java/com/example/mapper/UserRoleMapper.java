package com.example.mapper; // 20240308 Day13

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.UserRole;

@Mapper
public interface UserRoleMapper {

	void insertUserRole(UserRole userRole);
	void deleteUserRole(UserRole userRole);
	List<UserRole> getUserRolesByUserNo(int userNo);
}
