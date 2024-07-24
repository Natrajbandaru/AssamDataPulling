package net.javaguides.springboot.main.common.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateListCommonMethods {
	 
	 //It well give a List of dateString frome date: 2023-03-01  toDate :2024-03-10" o/p :2024-03-10
	  public  List<String> getDateRange(String startDateStr, String endDateStr) {
	        List<String> dateRange = new ArrayList<>();
	        
	        LocalDate startDate = LocalDate.parse(startDateStr);
	        LocalDate endDate = LocalDate.parse(endDateStr);
	        
	        while (!startDate.isAfter(endDate)) {
	            dateRange.add(startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	            startDate = startDate.plusDays(1);
	        }
	        return dateRange;
	    }
	  
	  //It well give a List of dateString frome date: 2023-03-01  toDate :2024-03-10"  o/p: 10-03-2024 
	  public  List<String> getDateRangeInReverseOutput(String startDateStr, String endDateStr) {
	        List<String> dateRange = new ArrayList<>();
	        
	        LocalDate startDate = LocalDate.parse(startDateStr);
	        LocalDate endDate = LocalDate.parse(endDateStr);
	        
	        while (!startDate.isAfter(endDate)) {
	            dateRange.add(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            startDate = startDate.plusDays(1);
	        }
	        return dateRange;
	    }
}
