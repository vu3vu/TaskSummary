package com.example.maintenanceuser1.service;

import java.util.List;

import com.example.maintenanceuser1.entity.Area;
import com.example.maintenanceuser1.entity.User;

public interface MaintenService {


	void insertUser(User usr);
	
	Area findAreaCode(String areaCode);
	
	List<Area> findAreaAll();
	
	List<User> findUser(User userName);
}
