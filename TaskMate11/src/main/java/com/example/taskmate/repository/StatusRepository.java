package com.example.taskmate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.taskmate.entity.Status;

@Mapper
public interface StatusRepository {

	//全権検索
	List<Status> selectAll();
	
	//１券検索
	Status selectByCode(@Param("statusCode") String statusCode); 
}
