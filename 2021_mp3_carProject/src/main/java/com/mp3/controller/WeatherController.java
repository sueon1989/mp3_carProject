package com.mp3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mp3.domain.AuthVO;
import com.mp3.domain.GpsVO;
import com.mp3.domain.MemberVO;
import com.mp3.domain.WeatherLocationVO;
import com.mp3.domain.WeatherVO;
import com.mp3.service.MypageService;
import com.mp3.service.WeatherService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller	// 스프링의 빈으로 인식
@Log4j		
@RequestMapping("/weather/*")	// /member로 시작하는 모든 처리를 MemberController가 처리하도록 지정
@AllArgsConstructor			// MemberController는 MemberService에 의존적: 생성자를 만들고 자동으로 주입하도록 함
public class WeatherController {

	private WeatherService service;	
	private MypageService serviceMypage;	
//	@Value("${weather.api.serviceKey}")
//	private String serviceKey;
	
	

	//map test
	@GetMapping("/gpsadd")
	public String gpsadd1(Model model,String gpsadd) {
		GpsVO gps = new GpsVO();
		gps.setGpsadd(gpsadd);
		log.info(gps);
		log.info("setGpsadd 테스트: "+gpsadd);
		
		serviceMypage.gpsadd(gpsadd);	// setGpsAdd (DB 저장)
		log.info(gpsadd);
		log.info("serviceMypage.gpsadd(gpsadd) 테스트: "+gpsadd);
		
		model.addAttribute("gpsadd", gpsadd);
		return "/weather/weatherInfo";
	}
	 
	 
	// 날씨정보
	@RequestMapping(value = "/weatherInfo", method = RequestMethod.GET)
	public String weather(WeatherVO weather, Model model) {

		log.info("날씨정보 조회");
		String serviceKey = "HnXMKIO%2FZdGJgfHsO%2Fxv2OlX0AR%2BuhAAV0zyLZlER5M7ehOb%2BHzBPgWBPmJ4BKr1FfIfxNxtKbERPR1t5gtnrA%3D%3D";
		
		// 예보일시 지정
//		String baseDate = "20210122"; 	// 예보일자
//      String baseTime = "0200"; 		// 예보시각		(0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회))
		weather = service.getNowDate();	// 예보일시
       
		// gps 위치정보 읽어오기
//      int nx = 60;  	//경도
//      int ny = 120;   //위도
		
		String gpsAdd = service.getGpsAdd();	// gps 주소정보
        WeatherLocationVO weatherLocation = service.getNowLocation(gpsAdd);	// 예보위치
        weather.setNx( Integer.parseInt(weatherLocation.getNx()) );
        weather.setNy( Integer.parseInt(weatherLocation.getNy()) );
        
        weather = service.getWeatherAPI(serviceKey, weather);	// 날씨조회 API
        log.info("날씨조회 API service 완료: "+ weather);
        log.info("날씨 DB등록 service 완료: "+ service.register(weather) +
        		" (baseDate: "+weather.getBaseDate()+" baseTime: "+weather.getBaseTime()+")");

        //
        weather = service.getWeatherInfo(weather);
        log.info("날씨 정보등록 service 완료: "+ weather);
        

        model.addAttribute("weatherLocation", weatherLocation);
        model.addAttribute("weather", weather);
		log.info("날씨정보 조회완료");
		
		return "/common/weather/weatherInfo";
		
	}
	
	@RequestMapping(value = "/carMagTipBySeason", method = RequestMethod.GET)
	public String carMagTipBySeason(WeatherVO weather, Model model) {
		return "/common/weather/carMagTipBySeason";
	}
	
	@RequestMapping(value = "/driveCourseBySeason", method = RequestMethod.GET)
	public String carMagTipByWeather(WeatherVO weather, Model model) {
		return "/common/weather/driveCourseBySeason";
	}
	
}
