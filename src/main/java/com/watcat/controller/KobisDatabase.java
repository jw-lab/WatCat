package com.watcat.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
import com.watcat.dto.Kobis.KobisDatabaseDto;
import com.watcat.service.KobisDatabaseService;
import com.watcat.service.KobisService;

@Controller
public class KobisDatabase {

	@Autowired
	KobisService kobisService;
	@Autowired
	KobisDatabaseService kobisDatabaseService;
	
	@RequestMapping("kobis/requestInput")
	public String requestInput() {
		return "Mypage/KobisRequestInput";
	}
	
	@ResponseBody
	@RequestMapping("kobis/collectionData")
	public List<KobisDatabaseDto> CollectionResult() throws Exception{
		List<KobisDatabaseDto> selectResult = kobisDatabaseService.CollectionResult();
		return selectResult; 
	}
	
	@ResponseBody
	@RequestMapping("kobis/requestResult")
	public Object requestResult(@RequestParam String yearInfo, @RequestParam String monthInfo) throws Exception {
		String startPoint = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?";
		String keyPoint = "key=";
		String key = "f5eef3421c602c6cb7ea224104795888";
		String targetPoint = "&targetDt=";
		String strUrl = null;
		
		List<DailyBoxOfficeDto> dataResponse = null;
		String day = null;
		int responseResultNum = 0; //for문 돌아가는 동안 리스폰드 받은 List 데이터 전체 합
		int databaseInsertSuccess = 0; // 데이터 인서트 결과
		int databaseInsertFail = 0; // 인서트 실패
		Map<String, String> AjaxResult = new HashMap<>();  
		for(int i = 1 ; i < 32 ; i++) {	 
			if( i < 10) {
				day = "0" + String.valueOf(i);
			}else {
				day = String.valueOf(i);
			}
			String Sumdate = yearInfo+ "-" + monthInfo + "-" + day;
			if(checkDate(Sumdate) == true) {
				strUrl = startPoint + keyPoint + key + targetPoint + yearInfo + monthInfo + day;
				dataResponse = kobisService.getDailyBoxOffice(strUrl);
				if(dataResponse != null) {
					responseResultNum += dataResponse.size();
					for(int j = 0; j < dataResponse.size() ; j++){
						dataResponse.get(j).setSearchDt(yearInfo+ monthInfo + day);
						String test = dataResponse.get(j).getOpenDt();
						try {
							kobisDatabaseService.BigDataInsert(dataResponse.get(j));							
							databaseInsertSuccess ++;
						}catch(Exception e) {
							databaseInsertFail ++;
						}
						
					}
				}
			}
		}
		
		AjaxResult.put("ResponseNum", String.valueOf(responseResultNum));
		AjaxResult.put("databaseInsertSuccess", String.valueOf(databaseInsertSuccess));
		AjaxResult.put("databaseInsertFail", String.valueOf(databaseInsertFail));
		return AjaxResult;
	}
	
	public boolean checkDate(String date) throws Exception {
		try {
			SimpleDateFormat  dateFormat = new  SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
}
