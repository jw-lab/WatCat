
  package com.watcat.service;
  
  import java.net.HttpURLConnection;
import java.net.URL;
import
  java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.watcat.dto.Kobis.BoxOfficeResultDto;
import
  com.watcat.dto.Kobis.DailyBoxOfficeDto;
import
  com.watcat.dto.Kobis.DailyBoxOfficeListDto;
  
  @Service public class KobisServiceImpl implements KobisService {
  
  
  @Override public List<DailyBoxOfficeDto> getDailyBoxOffice(String strUrl)
  throws Exception { List<DailyBoxOfficeDto> itemList = null;
  
  URL url = null; HttpURLConnection urlConn = null;
  
  try { url = new URL(strUrl); urlConn =
  (HttpURLConnection)url.openConnection(); urlConn.setRequestMethod("GET");
  
  JAXBContext jc = JAXBContext.newInstance(BoxOfficeResultDto.class);
  Unmarshaller um = jc.createUnmarshaller();
  
  BoxOfficeResultDto officeResult = (BoxOfficeResultDto)um.unmarshal(url);
  DailyBoxOfficeListDto list = officeResult.getDailyBoxOfficeList();
  
  itemList = list.getDailyBoxOffice(); } catch (Exception e) {
  e.printStackTrace(); } finally { if (urlConn != null) { urlConn.disconnect();
  } }
  
  return itemList; }
  
  }
 