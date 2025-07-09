package com.example.taskmate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmate.entity.Status;
import com.example.taskmate.repository.StatusRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //初期化が必要なフィールドを自動で初期化
public class StatusServiceImpl implements StatusService {

	//まず最初に利用するインフラ層のオブジェクトをDIする
	//そして@RACをつける
	//これでstatusRepositoryにMyBatisが自動作成してBean化してあるオブジェクトをDIする
	private final StatusRepository statusRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Status> findAll() {
		
		//①@Transactional(readOnly = true) を付ける
		List<Status> list = statusRepository.selectAll();
		
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public Status findByCode(String statusCode) {
		
		Status status = statusRepository.selectByCode(statusCode);
		
		return status;
	}

}
