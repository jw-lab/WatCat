package com.watcat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView KobisRequestInput() {
		ModelAndView mv = new ModelAndView("MyPage/KobisRequestInput");
		mv.addObject("pageName", "KobisRequestInput");
		return mv;
	}

	// 수집된 api데이터 리스트형태로 리턴
	@ResponseBody
	@RequestMapping("kobis/collectionData")
	public List<KobisDatabaseDto> CollectionResult() throws Exception {
		List<KobisDatabaseDto> selectResult = kobisDatabaseService.CollectionResult();
		return selectResult;
	}

	// api 데이터 월단위 자동 수집
	@ResponseBody
	@RequestMapping("kobis/requestResult")
	public Object requestResult(@RequestParam String yearInfo, @RequestParam String monthInfo) throws Exception {
		String startPoint = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?";
		String keyPoint = "key=";
		String key = "f5eef3421c602c6cb7ea224104795888";
		String targetPoint = "&targetDt=";
		String strUrl = null;

		List<DailyBoxOfficeDto> APIResponse = null;
		String dayInfo = null;
		int responseResultNum = 0; // for문 돌아가는 동안 리스폰드 받은 List 데이터 전체 합
		int databaseInsertSuccess = 0; // 데이터 인서트 결과
		int databaseInsertFail = 0; // 인서트 실패
		Map<String, String> AjaxResult = new HashMap<>();

		for (int i = 1; i < 32; i++) {
			if (i < 10) {
				dayInfo = "0" + String.valueOf(i);
			} else {
				dayInfo = String.valueOf(i);
			}
			String SumDateString = yearInfo + "-" + monthInfo + "-" + dayInfo;
			if (checkDate(SumDateString) == true) {
				strUrl = startPoint + keyPoint + key + targetPoint + yearInfo + monthInfo + dayInfo;
				APIResponse = kobisService.getDailyBoxOffice(strUrl);
				if (APIResponse != null) {
					responseResultNum += APIResponse.size();
					for (int j = 0; j < APIResponse.size(); j++) {
						APIResponse.get(j).setSearchDt(yearInfo + monthInfo + dayInfo);
						try {
							kobisDatabaseService.BigDataInsert(APIResponse.get(j));
							databaseInsertSuccess++;
						} catch (Exception e) {
							databaseInsertFail++;
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

	// 날짜 유효성 체크 메서드
	public boolean checkDate(String date) throws Exception {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	//Kobis API AutoThread
	@ResponseBody
	@RequestMapping("kobis/AutoThread")
	public void KobisAutoThread() {
		new Thread(){
			public void run(){
				String startPoint = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?";
				String keyPoint = "key=";
				String key = "f5eef3421c602c6cb7ea224104795888";
				String targetPoint = "&targetDt=";
				String strUrl = startPoint + keyPoint + key + targetPoint;
				List<DailyBoxOfficeDto> APIResponse;
				
				int responseResultNum = 0; // for문 돌아가는 동안 리스폰드 받은 List 데이터 전체 합
				int databaseInsertSuccess = 0; // 데이터 인서트 결과
				int databaseInsertFail = 0; // 인서트 실패
				int threadSleep = 60; // 스레드 동작 주기 (분)
				
				LocalDate yesterDay;
				String strYesterDay;
				String replaceYesterDay;
				
				try{
					while(true) {
						
						yesterDay = LocalDate.now().minusDays(1);
						strYesterDay = yesterDay.toString();
						replaceYesterDay = strYesterDay.replace("-", "");
						
						if (checkDate(strYesterDay) == true) {
							strUrl += replaceYesterDay;
							APIResponse = kobisService.getDailyBoxOffice(strUrl);
							if (APIResponse != null) {
								responseResultNum += APIResponse.size();
								for (int i = 0; i < APIResponse.size(); i++) {
									APIResponse.get(i).setSearchDt(replaceYesterDay);
									try {
										kobisDatabaseService.BigDataInsert(APIResponse.get(i));
										databaseInsertSuccess++;
									} catch (Exception e) {
										databaseInsertFail++;
									}

								}
							}
						}
						System.out.println("Kobis AutoThread 데이터 수집"); // 콘솔 출력 확인용
						System.out.println(yesterDay);
						System.out.println("조회된 데이터 : " + responseResultNum);
						System.out.println("Insert Success : " + databaseInsertSuccess);
						System.out.println("Insert Fail : " + databaseInsertFail);
//						Thread.sleep(1000*60*threadSleep);
						Thread.sleep(10000);//1000 = 1초
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	// 통계 페이지
	@RequestMapping("kobis/total")
	public ModelAndView KobisTotal() throws Exception{
		ModelAndView mv = new ModelAndView("kobisTotal");
		// 랭킹 1위 최다수 영화
		List<KobisDatabaseDto> Rank1Best = kobisDatabaseService.Rank1Best();
		// 랭킹 진입 최다수 영화
		List<KobisDatabaseDto> RankInBest = kobisDatabaseService.RankInBest();
		// 일매출 랭킹
		List<KobisDatabaseDto> SalesByDayBest = kobisDatabaseService.SalesByDayBest();
		// 누적 내출 랭킹
		List<KobisDatabaseDto> SalesBest = kobisDatabaseService.SalesBest();
		// 당일 관객수 랭킹
		List<KobisDatabaseDto> AudiCntBest = kobisDatabaseService.AudiCntBest();
		// 누적 관객수 랭킹
		List<KobisDatabaseDto> AudiAccBest = kobisDatabaseService.AudiAccBest();
		mv.addObject("Rank1Best", Rank1Best);
		mv.addObject("RankInBest", RankInBest);
		mv.addObject("SalesByDayBest", SalesByDayBest);
		mv.addObject("SalesBest", SalesBest);
		mv.addObject("AudiCntBest", AudiCntBest);
		mv.addObject("AudiAccBest", AudiAccBest);
		return mv;
	}
}

