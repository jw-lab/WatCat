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

	@Override // 랭킹 1위 최다수 영화
	public List<KobisDatabaseDto> Rank1Best() throws Exception {
		return kobisDatabaseMapper.Rank1Best();
	}

	@Override // 랭킹 진입 최다수 영화
	public List<KobisDatabaseDto> RankInBest() throws Exception {
		return kobisDatabaseMapper.RankInBest();
	}

	@Override // 일매출 랭킹
	public List<KobisDatabaseDto> SalesByDayBest() throws Exception {
		return kobisDatabaseMapper.SalesByDayBest();
	}

	@Override // 누적 내출 랭킹
	public List<KobisDatabaseDto> SalesBest() throws Exception {
		return kobisDatabaseMapper.SalesBest();
	}

	@Override // 당일 관객수 랭킹
	public List<KobisDatabaseDto> AudiCntBest() throws Exception {
		return kobisDatabaseMapper.AudiCntBest();
	}

	@Override // 누적 관객수 랭킹
	public List<KobisDatabaseDto> AudiAccBest() throws Exception {
		return kobisDatabaseMapper.AudiAccBest();
	}

}
