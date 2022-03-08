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
	private KobisService kobisService;
	
	@Autowired
	private UrlParser urlParser;

//	@ResponseBody
//	@GetMapping("/watcat/kobis")
//	public String getMovieRank() throws Exception {
//		StringBuilder result = new StringBuilder();
//
//		String strUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?"
//				+ "key=bb337acd8ff9a1fcda9c16e0d0550816" + "&targetDt=20220301";
//
//		URL url = null;
//		HttpURLConnection urlConn = null;
//
//		url = new URL(strUrl);
//		urlConn = (HttpURLConnection) url.openConnection();
//		urlConn.setRequestMethod("GET");
//
//		BufferedReader br;
//
//		br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
//
//		String returnLine;
//
//		while ((returnLine = br.readLine()) != null) {
//			result.append(returnLine + "\n\r");
//		}
//
//		urlConn.disconnect();
//
//		return result.toString();
//	}

	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.GET)
	public String getMovieRank() throws Exception {
		return "/kobis/rank";
	}


	@ResponseBody 
	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.POST) 
	public Object getMovieRank(@RequestParam("date") String date)
			throws Exception {

		String strUrl = urlParser.getUrl(date);
		List<DailyBoxOfficeDto> boxOffice = kobisService.getDailyBoxOffice(strUrl);

		return boxOffice;
	}
	

}
