package com.watcat.dto.Kobis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "dailyBoxOffice")
@XmlAccessorType(XmlAccessType.FIELD)
public class DailyBoxOfficeDto {

	@XmlElement(name = "rnum")
	private String rnum;
	
	@XmlElement(name = "rank")
	private String rank;
	
	@XmlElement(name="rankInten")
	private String rankInten;
	
	@XmlElement(name="rankOldAndNew")
	private String rankOldAndNew;
	
	@XmlElement(name="movieCd")
	private String movieCd;
	
	@XmlElement(name="movieNm")
	private String movieNm;
	
	@XmlElement(name="rnum")
	private String openDt;
	
	@XmlElement(name="salesAmt")
	private String salesAmt;
	
	@XmlElement(name="salesShare")
	private String salesShare;
	
	@XmlElement(name="salesInten")
	private String salesInten;
	
	@XmlElement(name="salesChange")
	private String salesChange;
	
	@XmlElement(name="salesAcc")
	private String salesAcc;
	
	@XmlElement(name="audiCnt")
	private String audiCnt;
	
	@XmlElement(name="audiInten")
	private String audiInten;
	
	@XmlElement(name="audiChange")
	private String audiChange;
	
	@XmlElement(name="audiAcc")
	private String audiAcc;
	
	@XmlElement(name="scrnCnt")
	private String scrnCnt;
	
	@XmlElement(name="showCnt")
	private String showCnt;
	

}
