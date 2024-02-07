package com.tenco.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LocateController {

	@GetMapping("/")
	public String mainPage() {
		return "layout/main";
	}

}
