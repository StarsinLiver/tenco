package com.tenco.bank.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenco.bank.dto.CustomChartDto;
import com.tenco.bank.service.ChartService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Autowired
	ChartService chartService;

	@GetMapping("/")
	public String mainPage(Model model) {
		List<CustomChartDto> userList = chartService.FindAllUserChartWeekly();
		
		if (userList == null || userList.isEmpty()) {
			// 음 뭐하지..
		}
		List<CustomChartDto> accountList = chartService.findAllAccountChartYearly();
		if (accountList == null || accountList.isEmpty()) {
			// 음 뭐하지..
		}
		List<CustomChartDto> historyList = chartService.findAllHistoryChartMonthly();
		if (historyList == null || historyList.isEmpty()) {
			// 음 뭐하지..
		}
		
		CustomChartDto[] userArray = userList.toArray(new CustomChartDto[userList.size()]);
		CustomChartDto[] accountArray = userList.toArray(new CustomChartDto[accountList.size()]);
		CustomChartDto[] historyArray = userList.toArray(new CustomChartDto[historyList.size()]);
		
		
		model.addAttribute("historyList" , historyArray);
		model.addAttribute("accountList" , accountArray);
		model.addAttribute("userList" , userArray);
		return "layout/main";
	}

}
