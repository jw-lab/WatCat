package com.watcat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.dto.Kobis.KobisDatabaseDto;
import com.watcat.mapper.KobisDatabaseMapper;

@Service
public class KobisDatabaseServiceImpl implements KobisDatabaseService{

	@Autowired
	KobisDatabaseMapper kobisDatabaseMapper;
	
	@Override
	public void BigDataInsert(DailyBoxOfficeDto dailyBoxOfficeDto) throws Exception {
		kobisDatabaseMapper.BigDatabaseInsert(dailyBoxOfficeDto);
	}

	@Override
	public List<KobisDatabaseDto> CollectionResult() throws Exception {
		return kobisDatabaseMapper.CollectionResult();
	}

}
