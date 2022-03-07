package com.watcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watcat.common.UrlParser;
import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.service.KobisService;

@Controller
public class KobisController {
	
	@Autowired
	private KobisService KobisService;
	
	@Autowired
	private UrlParser urlParser;
	
	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.GET)
	public String covid19infoUrlAjax() throws Exception {
		return "/kobis/rank";
	}


	@ResponseBody 
	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.POST) 
	public Object getCovid19InfoAjax(@RequestParam("date") String date)
			throws Exception {

		String strUrl = urlParser.getUrl(date);
		List<DailyBoxOfficeDto> boxOffice = KobisService.getDailyBoxOffice(strUrl);

		return boxOffice;
	}
	

}
