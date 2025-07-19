package com.example.maintenanceuser1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.maintenanceuser1.entity.Area;
import com.example.maintenanceuser1.entity.User;

@Mapper
public interface MaintenMapper {
	// 一覧全件検索
		List<User> selectListAll();
		
		Area findAreaCode(@Param("areaCode") String areaCode);
		
		List<Area> selectAreaAll();//ｘｍlと連携
		
}
//mapperはXMLとの整合