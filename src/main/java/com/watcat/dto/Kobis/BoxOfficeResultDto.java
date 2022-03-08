package com.watcat.dto.Kobis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="boxOfficeResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoxOfficeResultDto {
	
	@XmlElement(name="boxofficeType")
	private String boxofficeType;

	@XmlElement(name="showRange")
	private String showRange;
	
	@XmlElement(name="dailyBoxOfficeList")
	private DailyBoxOfficeListDto dailyBoxOfficeList;
	
//	@XmlElement(name="dailyBoxOfficeList")
//	private String dailyBoxOfficeList;

}
