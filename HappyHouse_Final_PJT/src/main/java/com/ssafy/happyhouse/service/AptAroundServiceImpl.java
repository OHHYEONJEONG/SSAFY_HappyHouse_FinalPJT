package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.EnvirInfoDto;
import com.ssafy.happyhouse.dto.StoreInfoDto;
import com.ssafy.happyhouse.mapper.AptAroundMapper;

@Service
public class AptAroundServiceImpl implements AptAroundService{
	@Autowired
	private AptAroundMapper around;
	@Override
	public List<EnvirInfoDto> getEnvirInfo(String sigugun, String dong) {
		// TODO Auto-generated method stub
		return around.selectEnvInfoByDong(sigugun, dong);
	}
	@Override
	public List<StoreInfoDto> getAllStoreInfo(String sido, String gugun, String dong) {
		// TODO Auto-generated method stub
		return around.selectStoreInfoByDong(sido, gugun, dong);
	}

}
