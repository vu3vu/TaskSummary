package com.example.maintenanceuser1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.maintenanceuser1.entity.User;
@Mapper
public interface MaintenUserAdd {

	//登録　（抽象メソッド）
	void insert(@Param("user") User usr);//プレースホルダ名"task"を指定
	
	
}
