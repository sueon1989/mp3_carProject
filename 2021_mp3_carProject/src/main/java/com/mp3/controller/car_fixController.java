package com.mp3.controller;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mp3.domain.CarRegistrationVO;
import com.mp3.domain.Car_fixVO;
import com.mp3.service.Car_fixService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j		
@RequestMapping("/fix/*")	 
@AllArgsConstructor
public class car_fixController {
	
	private Car_fixService service;
	 //등록
	@GetMapping("/car_fix")
	public String  car_fix() {
		return "common/record/car_fix";
	}
	
	@PostMapping("/car_fix")	
	public String  car_fix1(Car_fixVO car_fix,Model model) {
		log.info("테스트입니다~."+car_fix);
		model.addAttribute("car_fix",service.car_fix(car_fix));
		return "common/record/car_fix";
	}
	

	
	@PostMapping("/car_fixUpdate")	
	public String  car_fixupdate(Car_fixVO car_fix,Model model) {
		log.info("테스트입니다~."+car_fix);
		model.addAttribute("car_fixUpdate",service.car_fixUpdate(car_fix));
		return "common/record/car_fixUpdate";
	}
	
	// 목록 보기
		@GetMapping("/car_fixUpdate")
		public String  car_fixlist(Principal principal,Model model){
			String member_id=principal.getName();
			Car_fixVO car_fix = new Car_fixVO();
			car_fix.setMember_id(member_id);
			Car_fixVO car_fixvo = service.car_fixlist(member_id);
			model.addAttribute("fixlist",service.car_fixlist(member_id));
			log.info("이거값도 찍히나??"+car_fixvo);
			return "common/record/car_fixUpdate";
			
		}

		
//		// 목록 보기
//		@PostMapping("/car_fixlist")
//		public String  car_fixlist() {
//			
//			return "common/record/car_fixlist";
//		}

}

