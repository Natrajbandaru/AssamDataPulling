package net.javaguides.springboot.main.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.controller.ReturnsController;

@Service
public class SheduledTimeService {
	
	/* Time Formate Should Be : '2024-01-08 12:25:00 */
	
	/*
	 * public final String SCHEDULED_DATE_TIME = "2024-01-06 16:41:00";
	 * 
	 * @Scheduled(fixedRate = FIXED_RATE) // Run every 5 seconds
	 * 
	 * @Scheduled(initialDelayString =
	 * "#{ T(net.javaguides.springboot.main.common.controller.CommonMethodes).calculateInitialDelay('"
	 * +SCHEDULED_DATE_TIME+"') }", fixedRate = 5000) public void mainData() {
	 * CommonMethodes s=new CommonMethodes(); s.calculateInitialDelay("");
	 * System.out.println("Enter"); }
	 */
	
	
    @Autowired
    SheduledRepository repo;
    
    @Autowired
	@Lazy
    SheduledTimeService sheduledTimeService;
    
    @Autowired
    ReturnsController returnsController;
    
    public String sdKey = "DEFAULT_VALUE"; // Default value

//	public void sheduledTimeService() {
//	    SheduledData findByUrlkeyName = repo.findByUrlkeyName("DRC03_KEY");
//        long initialDelay = calculateInitialDelay(sdKey, findByUrlkeyName.sheduledDate);
//
//        // Schedule the task with a fixed delay between executions
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.schedule(() -> returnsController.drc03DatagetDrc03List(), initialDelay, TimeUnit.MILLISECONDS);
//	}
//	
    
    //Method For GivenTime is Greater Or Equel Or less
    public String givenTimeIsGreaterOrEqual(String givenDate) {
    	
    	//Given Date and Time
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime scheduledDateTime = LocalDateTime.parse(givenDate, formatter);
		
		// Present Date and Time
	    LocalDateTime presentDateTime = LocalDateTime.now();
		String formatedDateTimeString = presentDateTime.format(formatter);
		LocalDateTime formatDateTime = LocalDateTime.parse(formatedDateTimeString, formatter);
		
		LocalDateTime scheduledDateTimeTruncated = scheduledDateTime.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime presentDateTimeTruncated = presentDateTime.truncatedTo(ChronoUnit.MINUTES);

		
		if (scheduledDateTimeTruncated.isAfter(presentDateTimeTruncated)) {
		    return "after";
		} else if (scheduledDateTimeTruncated.isBefore(presentDateTimeTruncated)) {
		    return "before";
		} else if (scheduledDateTimeTruncated.isEqual(presentDateTimeTruncated)) {
		    return "equal";
		}
		return "";
    }
    //common 7297
  //  @Scheduled(fixedRate = 5000)
   // Note: It well Run If sheduled Time is Greater and runs with 5 Sec 
    public void sheduledTimeService() {
	    //SheduledData findByUrlkeyName = repo.findByUrlkeyName("DRC03_KEY");
    	List<SheduledData> listOfShedules = repo.findAll();
    	
    	for(int i=0; i<listOfShedules.size();i++) {
			String sheduledDate = listOfShedules.get(i).sheduledDate;
    		if(listOfShedules.get(i).status != null) {
    			if(listOfShedules.get(i).status.equals("pending")) {
    				if(listOfShedules.get(i).urlkeyName.equals("DRC03_KEY")) {
    					String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(listOfShedules.get(i).sheduledDate);
    					System.out.println(givenTimeIsGreaterOrEqual);
    					if(!givenTimeIsGreaterOrEqual.equals("after")) {    						
    						long initialDelay = calculateInitialDelay(sdKey, listOfShedules.get(i).sheduledDate);
    						ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    						scheduler.schedule(() -> returnsController.drc03DatagetDrc03List(sheduledDate), initialDelay, TimeUnit.MILLISECONDS);
    						SheduledData sheduledData = listOfShedules.get(i);
    						sheduledData.status="completed";
    						repo.save(sheduledData);
    					}
    				}else if(listOfShedules.get(i).urlkeyName.equals("CRN_KEY")) {
    					String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(listOfShedules.get(i).sheduledDate);
    					if(!givenTimeIsGreaterOrEqual.equals("after")) {  
    						long initialDelay = calculateInitialDelay(sdKey, listOfShedules.get(i).sheduledDate);
    						ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    						scheduler.schedule(() -> returnsController.drc03DatagetCrnList(sheduledDate), initialDelay, TimeUnit.MILLISECONDS);
    						SheduledData sheduledData = listOfShedules.get(i);
    						sheduledData.status="completed";
    						repo.save(sheduledData);
    					}
    				}
    				else if(listOfShedules.get(i).urlkeyName.equals("CRN_DRC03_KEY")) {
    					String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(listOfShedules.get(i).sheduledDate);
    					if(!givenTimeIsGreaterOrEqual.equals("after")) {  
    						long initialDelay = calculateInitialDelay(sdKey, listOfShedules.get(i).sheduledDate);
    						ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    						scheduler.schedule(() -> returnsController.generateCRNlistForDrc03SaveDrc03Data(sheduledDate,false), initialDelay, TimeUnit.MILLISECONDS);
    						SheduledData sheduledData = listOfShedules.get(i);
    						sheduledData.status="completed";
    						repo.save(sheduledData);
    					}
    				}
    				else if(listOfShedules.get(i).urlkeyName.equals("CRN_DRC01_KEY")) {
    					String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(listOfShedules.get(i).sheduledDate);
    					if(!givenTimeIsGreaterOrEqual.equals("after")) {  
    						long initialDelay = calculateInitialDelay(sdKey, listOfShedules.get(i).sheduledDate);
    						ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    						scheduler.schedule(() -> returnsController.generateCRNlistForDrc01SaveDrc01Data(sheduledDate,false), initialDelay, TimeUnit.MILLISECONDS);
    						SheduledData sheduledData = listOfShedules.get(i);
    						sheduledData.status="completed";
    						repo.save(sheduledData);
    					}
    				}
    				else if(listOfShedules.get(i).urlkeyName.equals("DRC07_LIST_KEY")) {
    					String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(listOfShedules.get(i).sheduledDate);
    					if(!givenTimeIsGreaterOrEqual.equals("after")) {  
    						long initialDelay = calculateInitialDelay(sdKey, listOfShedules.get(i).sheduledDate);
    						ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    						scheduler.schedule(() -> returnsController.generateDRC07ListForItemIDSaveDrc07DataByDemaindId(sheduledDate), initialDelay, TimeUnit.MILLISECONDS);
    						SheduledData sheduledData = listOfShedules.get(i);
    						sheduledData.status="completed";
    						repo.save(sheduledData);
    					}
    				}

    				
    			}
    		}
    	}
    }
  
	// sub-- Calculation of date and time to sheduled date dormate
    private long calculateInitialDelay(String sdKeyValue, String targetDateTimeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentDate = new Date();
            Date targetDate = dateFormat.parse(targetDateTimeString);
            return targetDate.getTime() - currentDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

	//Method For Present Time
    public String presentDateAndTime() {
		  LocalDateTime currentDateTime = LocalDateTime.now();
	      currentDateTime = currentDateTime.withSecond(0);
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	      String formattedDateTime = currentDateTime.format(formatter);
		return formattedDateTime;
    }
	

}
