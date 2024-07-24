package net.javaguides.springboot.main.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.main.controller.ReturnsController;

@Controller
public class InduvedualMethodsCallingByDateList {
	
	@Autowired
	DateListCommonMethods dateCommonMethods;
	
	
	@Autowired
	@Lazy
	ReturnsController returnsMethod;
	
	@RequestMapping("/drc07ListDt")
	public void callDrc07ByListDates() throws Exception {
		String fromDt="2023-11-01";
	    String toDate="2024-03-14";
		List<String> dateRange = dateCommonMethods.getDateRange(fromDt,toDate);
		System.out.println(dateRange);

		for(int i=0;i<dateRange.size();i++) {
			System.out.println(dateRange.get(i));
			returnsMethod.generateDRC07ListForItemIDSaveDrc07DataByDemaindId(dateRange.get(i));
		}
		
	}
}
