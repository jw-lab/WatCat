package com.watcat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watcat.common.UrlParser;
import com.watcat.service.KobisService;

@Controller
public class KobisController {

	@Autowired
	private KobisService kobisService;

	@Autowired
	private UrlParser urlParser;

	@ResponseBody
	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.POST)
	public String getMovieRank() throws Exception {
		StringBuilder result = new StringBuilder();

		String strUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?"
				+ "key=bb337acd8ff9a1fcda9c16e0d0550816" + "&targetDt=20220301";

		URL url = null;
		HttpURLConnection urlConn = null;

		url = new URL(strUrl);
		urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod("GET");

		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

		String returnLine;

		while ((returnLine = br.readLine()) != null) {
			result.append(returnLine + "\n\r");
		}

		urlConn.disconnect();

		return result.toString();
	}

	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.GET)
	public String memberAjax() throws Exception {
		return "/kobis/rank";
	}

//	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.POST, produces = "application/json; charset=utf8")
//	public @ResponseBody HashMap<String, Object> method(@RequestBody Map<String, Object> param) {
//
//		Gson gson = new Gson();
//
//		JsonParser jparser = new JsonParser();
//
//		JsonElement rank = jparser.parse(param.get("rank").toString());
//		JsonElement movieNm = jparser.parse(param.get("movieNm").toString());
//		JsonElement openDt = jparser.parse(param.get("openDt").toString());
//		JsonElement auiAcc = jparser.parse(param.get("auiAcc").toString());
//
//		List<String> rankList = gson.fromJson(rank, (new TypeToken<List<String>>() {
//		}).getType());
//		List<String> movieNmList = gson.fromJson(movieNm, (new TypeToken<List<String>>() {
//		}).getType());
//		List<String> openDtList = gson.fromJson(openDt, (new TypeToken<List<String>>() {
//		}).getType());
//		List<String> auiAccList = gson.fromJson(auiAcc, (new TypeToken<List<String>>() {
//		}).getType());
//		
//		System.out.println(rankList);
//
//		return null;
//	}
}
