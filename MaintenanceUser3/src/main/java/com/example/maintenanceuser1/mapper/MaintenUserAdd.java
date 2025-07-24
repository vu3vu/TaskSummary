package com.example.maintenanceuser1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.maintenanceuser1.entity.User;
@Mapper
public interface MaintenUserAdd {

	//登録　（抽象メソッド）
	void insert(@Param("user") User usr);//プレースホルダ名"task"を指定
	
	//このSQLはString型のパラメータを１つ受け取る
	List<User> searchUser(@Param("user") User usr);
}
