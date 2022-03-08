package com.watcat.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlParser {
	
//	@Value 어노테이션은 application.properties 파일 내에 설정된 값을 가져올 수 있음
	@Value("${custom.serviceKey}")
	private String serviceKey;

	
	private String endPoint = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?";
	private String keyFunc = "key=";
	private String targetDt = "&targetDt=20220301";
	
	public String getUrl() {
		String strUrl = endPoint + keyFunc + serviceKey + targetDt;
		
		return strUrl;
	}
}
