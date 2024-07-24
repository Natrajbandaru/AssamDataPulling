package net.javaguides.springboot.main.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.main.common.LoginRepository.DailyReportDataRepo;
import net.javaguides.springboot.main.common.model.DailyReportDataModel;

@Service
public class DailyReportService {

	@Autowired
	DailyReportDataRepo  dailyDataRepo;
	
	public void dailyreport(String urlKeyName, String paramsData,String date) throws ParseException {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringToDate = dateFormat.parse(date);
		
		DailyReportDataModel dailyData=new DailyReportDataModel();
	    dailyData.urlName = urlKeyName;
	    dailyData.insertedDate = stringToDate;
	    dailyData.parentParams = paramsData;
	    dailyDataRepo.save(dailyData);
		
	}
}
