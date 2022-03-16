package com.watcat.service;

import java.util.List;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.dto.Kobis.KobisDatabaseDto;

public interface KobisDatabaseService {

	void BigDataInsert(DailyBoxOfficeDto dailyBoxOfficeDto) throws Exception;

	List<KobisDatabaseDto> CollectionResult() throws Exception;

}
