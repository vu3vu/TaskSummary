package com.example.maintenanceuser1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.maintenanceuser1.entity.User;

@Mapper
public interface MaintenMapper {
	// 一覧全件検索
		List<User> selectListAll();
}