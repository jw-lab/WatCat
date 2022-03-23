
package com.watcat.dto.Kobis;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data

@XmlRootElement(name = "dailyBoxOfficeList")

@XmlAccessorType(XmlAccessType.FIELD)
public class DailyBoxOfficeListDto {

	// @XmlElement(name="dailyBoxOffice") // private String dailyBoxOffice;

	@XmlElement(name = "dailyBoxOffice")
	private List<DailyBoxOfficeDto> dailyBoxOffice;

}
