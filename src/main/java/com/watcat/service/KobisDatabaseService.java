package com.watcat.service;

import java.util.List;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.dto.Kobis.KobisDatabaseDto;

public interface KobisDatabaseService {

	void BigDataInsert(DailyBoxOfficeDto dailyBoxOfficeDto) throws Exception;

	List<KobisDatabaseDto> CollectionResult() throws Exception;

	List<KobisDatabaseDto> Rank1Best() throws Exception; // 랭킹 1위 최다수 영화

	List<KobisDatabaseDto> RankInBest() throws Exception; // 랭킹 진입 최다수 영화

	List<KobisDatabaseDto> SalesByDayBest() throws Exception; // 일매출 랭킹

	List<KobisDatabaseDto> SalesBest() throws Exception; // 누적 내출 랭킹

	List<KobisDatabaseDto> AudiCntBest() throws Exception; // 당일 관객수 랭킹

	List<KobisDatabaseDto> AudiAccBest() throws Exception; // 누적 관객수 랭킹

}
