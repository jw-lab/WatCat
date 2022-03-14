package com.watcat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.watcat.dto.reviewDto;
import com.watcat.dto.movie.MovieWishDto;
import com.watcat.service.MovieRecommendService;

@Controller
public class MovieRecommendController {

	@Autowired
	private MovieRecommendService movieRecommendService;
	
	//추천영화 페이지
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView movieRecommend(@RequestParam(required = false,defaultValue = "1") int pageNum) throws Exception{
		ModelAndView mv = new ModelAndView("movie/recommend");
		PageInfo<reviewDto> reviewList = new PageInfo<>(movieRecommendService.selectReviewList(pageNum),5); 
		mv.addObject("reviewList", reviewList);
		return mv;
	}
	
	//영화 리뷰 상세보기 페이지
	@RequestMapping(value="/review/detail", method = RequestMethod.GET)
	public ModelAndView reviewDetail(int idx) throws Exception{
		ModelAndView mv = new ModelAndView("movie/reviewDetail");
		reviewDto review = movieRecommendService.getReviewDetail(idx);
		mv.addObject("review", review);
		return mv;
	}
	
	//영화 상세보기 페이지 이동
	@RequestMapping(value="/movie/detail", method = RequestMethod.GET)
	public String movieDetail(int movieId, Model model) throws Exception {
		model.addAttribute("movieId", movieId);
		return "movie/detail";
	}

	
	//영화 검색 페이지
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String movieSearch() throws Exception {
		return "movie/search";
	}
	
	//찜하기 추가
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.POST)
	public void insertMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendService.insertMovieWish(movieWish);
	}
	//찜하기 해제
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.DELETE)
	public void deleteMovieWish(MovieWishDto movieWish) throws Exception {
		movieRecommendService.deleteMovieWish(movieWish);
	}
	//찜하기 list 정보
	@ResponseBody
	@RequestMapping(value = "/movie/wish", method = RequestMethod.GET)
	public Object selectMovieWish(MovieWishDto movieWish) throws Exception {
		List<MovieWishDto> movieWishList = movieRecommendService.selectMovieWish(movieWish);
		return movieWishList;
	}
	
	//리뷰 등록 post
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String insertReview(reviewDto reviewDto) throws Exception {
		movieRecommendService.insertReview(reviewDto);
		return "redirect:/";
	}
	//네이버 api 시작
	@ResponseBody
	@RequestMapping("/movie/detail/api")
	public Object naverAPIRequest(String title,String genre) throws Exception {
		String clientId = "wiu_SsXoI6q0Mhqd3BA_"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "G0xqILsT0L"; //애플리케이션 클라이언트 시크릿값"

        String text = null;
        try {
            text = URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
       
        String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // json 결과
        apiURL += "&display=1"; // 1개만 가져옴
        apiURL += "&genre="+genre; // 장르 추가 - title이 겹치는 경우 사용

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
        	jsonObject = (JSONObject)jsonParser.parse(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.get("items");
	}
	
	
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    } //네이버 api 종료
}
