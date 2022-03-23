package com.watcat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.service.KobisService;

@Controller
public class KobisController {

	@Autowired
	private KobisService kobisService;


	@ResponseBody
	@RequestMapping(value = "watcat/kobis/api", method = RequestMethod.POST)
	public Object memberAjaxGo(@RequestParam("date") String date) throws Exception {
		String strUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?"
		+ "key=bb337acd8ff9a1fcda9c16e0d0550816" + "&targetDt=" + date;

		List<DailyBoxOfficeDto> kobis = kobisService.getDailyBoxOffice(strUrl);
		return kobis;
	}


	@RequestMapping(value = "/watcat/kobis", method = RequestMethod.GET)
	public String memberAjax() throws Exception {
		return "kobis/rank_list";
	}

	
//	@ResponseBody
//	@RequestMapping(value = "watcat/kobis/api", method = RequestMethod.GET)
//	public Object getMovieRank(String rank , String movieNm, String openDt, String audiAcc) throws Exception {
//		StringBuilder result = new StringBuilder();
//
//		String strUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?"
//				+ "key=bb337acd8ff9a1fcda9c16e0d0550816" + "&targetDt=20220301";
//
//		URL url = null;
//		HttpURLConnection urlConn = null;
//		
//		
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        
//        JSONParser jsonParser = new JSONParser();
////        JSONObject jsonObject = new JSONObject();
//        
//        StringBuilder stringBuilder = new StringBuilder();
//        String content = stringBuilder.toString();
//        JSONObject jsonObject = new JSONObject();
//        
//        
////        for (int i = 0; i < json.length(); i++){
////        	  JSONObject jsonObject = json.getJSONObject(i);
////        	  
////            jsonObject.put("rank", rank);
////            jsonObject.put("movieNm", movieNm);
////            jsonObject.put("openDt", openDt);
////            jsonObject.put("audiAcc", audiAcc);
////        	}
//
////        jsonObject.put("rank", rank);
////        jsonObject.put("movieNm", movieNm);
////        jsonObject.put("openDt", openDt);
////        jsonObject.put("audiAcc", audiAcc);
//        
//        String responseBody = get(strUrl);
//        
//        try {
//        	jsonObject = (JSONObject)jsonParser.parse(responseBody);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
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
//		return jsonObject.get(result.toString());
//				
//	}

//	private static String get(String strUrl) {
//		HttpURLConnection con = connect(strUrl);
//		try {
//			con.setRequestMethod("GET");
//
//			int responseCode = con.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//				return readBody(con.getInputStream());
//			} else { // 에러 발생
//				return readBody(con.getErrorStream());
//			}
//		} catch (IOException e) {
//			throw new RuntimeException("API 요청과 응답 실패", e);
//		} finally {
//			con.disconnect();
//		}
//	}
//
//	private static HttpURLConnection connect(String strUrl) {
//		try {
//			URL url = new URL(strUrl);
//			return (HttpURLConnection) url.openConnection();
//		} catch (MalformedURLException e) {
//			throw new RuntimeException("API URL이 잘못되었습니다. : " + strUrl, e);
//		} catch (IOException e) {
//			throw new RuntimeException("연결이 실패했습니다. : " + strUrl, e);
//		}
//	}
//
//	private static String readBody(InputStream body) {
//		InputStreamReader streamReader = new InputStreamReader(body);
//
//		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
//			StringBuilder responseBody = new StringBuilder();
//
//			String line;
//			while ((line = lineReader.readLine()) != null) {
//				responseBody.append(line);
//			}
//
//			return responseBody.toString();
//		} catch (IOException e) {
//			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
//		}
//	}

}
