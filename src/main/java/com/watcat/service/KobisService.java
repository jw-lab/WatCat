
  package com.watcat.service;
  
  import java.util.List;

import com.watcat.dto.Kobis.DailyBoxOfficeDto;
  
  public interface KobisService {
  
  List<DailyBoxOfficeDto> getDailyBoxOffice(String strUrl)throws Exception;
  
  
  }
 