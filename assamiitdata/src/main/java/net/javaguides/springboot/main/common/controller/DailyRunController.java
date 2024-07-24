package net.javaguides.springboot.main.common.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.main.controller.ReturnsController;


@RestController
@RequestMapping("/returnss")
public class DailyRunController {
	
	@Autowired
	ReturnsController returnsController;
	
	public String presentDate() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
	}
	
	
	//@Scheduled(cron = "0 05 19 * * ?")
	public void Drc03Data() throws Exception {
		String generateCRNlistForDrc03SaveDrc03Data = returnsController.generateCRNlistForDrc03SaveDrc03Data(presentDate(),false);
	}
	
	//@Scheduled(cron = "0 05 19 * * ?")
	public void Drc01Data() throws Exception {
		String generateCRNlistForDrc01SaveDrc01Data = returnsController.generateCRNlistForDrc01SaveDrc01Data(presentDate(),false);
	}
	
	//@Scheduled(cron = "0 05 19 * * ?")
	public void Drc07Data() throws Exception {
		String drc07 = returnsController.generateDRC07ListForItemIDSaveDrc07DataByDemaindId(presentDate());
	}
	
	
	


}
