package net.javaguides.springboot.main.common.controller;
import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.common.service.SheduledTimeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/api")
public class MyController {

    public String sdKey = "sss"; // Default value
    
    @Autowired
    SheduledRepository repo;
    
    @Autowired
    SheduledTimeService sheduledTimeService;
    
    @GetMapping("/getMyProperty")
    //@Scheduled(initialDelayString = "#{@calculateInitialDelay(sdKey)}", fixedRate = 5000)
    public String getMyProperty() {
        System.out.println("pppss");
        return "";
    }

//    // Method to calculate the initial delay dynamically
//    public long calculateInitialDelay(String sdKeyValue) {
//        // Use sdKeyValue or any other dynamic logic to calculate the initial delay
//        return 1;// Your logic to calculate initial delay in milliseconds;
//    }
//    
//    @GetMapping("/triggerTask")
//    public void triggerTask() {
//        long initialDelay = calculateInitialDelay(sdKey);
//
//        // Schedule the task with a fixed-rate schedule and the calculated initial delay
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(() -> getMyProperty(), initialDelay, 5000, TimeUnit.MILLISECONDS);
//    }
    
    @GetMapping("/triggerTask")
   // @Scheduled(fixedRate = 5000)
    public void triggerTask() {
    	
    	sheduledTimeService.sheduledTimeService();
//    	System.out.println("Ent1");
//        // Calculate the initial delay until the specified date and time
//        SheduledData findByUrlkeyName = repo.findByUrlkeyName("DRC03_KEY");
//        System.out.println(findByUrlkeyName.sheduledDate+"---findByUrlkeyName");
//		System.out.println("DATE fORMATE---'2024-01-08 12:25:00'");
//        long initialDelay = calculateInitialDelay(sdKey, findByUrlkeyName.sheduledDate);
//
//        // Schedule the task with a fixed delay between executions
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.schedule(() -> getMyProperty(), initialDelay, TimeUnit.MILLISECONDS);
    }

    private long calculateInitialDelay(String sdKeyValue, String targetDateTimeString) {
        // Use sdKeyValue or any other dynamic logic to calculate the initial delay
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentDate = new Date();
            Date targetDate = dateFormat.parse(targetDateTimeString);

            // Calculate the initial delay until the specified date and time
            return targetDate.getTime() - currentDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception as needed
            return 0;
        }
    }
}
