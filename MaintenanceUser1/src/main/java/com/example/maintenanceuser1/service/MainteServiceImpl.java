package com.example.maintenanceuser1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.maintenanceuser1.entity.User;
import com.example.maintenanceuser1.mapper.MaintenMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //初期化が必要なフィールドを自動で初期化
public class MainteServiceImpl implements MaintenService {

	//利用するインフラ層のクラスをDIする
	private final MaintenMapper mainteMapper;
	
	@Override
	public List<User> findListAll() {
		// TODO 自動生成されたメソッド・スタブ
		List<User> usr = mainteMapper.selectListAll();
		
		return usr;
	}

}
