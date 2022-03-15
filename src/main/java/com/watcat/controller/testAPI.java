package com.watcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testAPI {

	@RequestMapping("kobis/requestInput")
	public String requestInput() {
		return "Mypage/requestInput";
	}
}
