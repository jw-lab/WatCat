package com.watcat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.dto.Kobis.KobisDatabaseDto;

@Mapper
public interface KobisDatabaseMapper {

	void BigDatabaseInsert(DailyBoxOfficeDto dailyBoxOfficeDto) throws Exception;

	List<KobisDatabaseDto> CollectionResult() throws Exception;

}
