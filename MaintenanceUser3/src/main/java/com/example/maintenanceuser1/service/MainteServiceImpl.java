package com.example.maintenanceuser1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.maintenanceuser1.entity.Area;
import com.example.maintenanceuser1.entity.User;
import com.example.maintenanceuser1.mapper.MaintenMapper;
import com.example.maintenanceuser1.mapper.MaintenUserAdd;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //初期化が必要なフィールドを自動で初期化
public class MainteServiceImpl implements MaintenService {

	//利用するインフラ層のクラスをDIする
	private final MaintenMapper mainteMapper;
	private final MaintenUserAdd maintenUserAdd;
	

	@Override
	public void insertUser(User usr) {
		
		maintenUserAdd.insert(usr);
		
	}
	@Override
	public List<Area> findAreaAll(){
		
		List<Area> area = mainteMapper.selectAreaAll();
		return area;
	}
	
	@Override
	public Area findAreaCode(String areaCode){
		//エリアコードを返すメソッド　→マッパと連携
		Area area = mainteMapper.findAreaCode(areaCode);
		
		return area;
	}
	@Override
	public List<User> findUser(User userName) {
		// 部分一致でユーザを検索
		//mapper を使う
		List<User> list = maintenUserAdd.searchUser(userName);
		
		return list;
	}

}
