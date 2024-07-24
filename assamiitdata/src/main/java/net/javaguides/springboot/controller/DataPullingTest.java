package net.javaguides.springboot.controller;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public class DataPullingTest {

private static int f;

//	 public static void main(String[] args) {
//	        // Set the specific day and time to start the loop
//	        LocalDateTime startTime = LocalDateTime.of(2024, 3, 4, 0, 0); // Year, Month, Day, Hour, Minute
//	        LocalDateTime endTime = LocalDateTime.of(2024, 3, 5, 0, 0); // End time is exclusive
//
//	        // Define the format for printing the time
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	        // Loop through the hours between the start and end times
//	        for (LocalDateTime time = startTime; time.isBefore(endTime); time = time.plusHours(1)) {
//	            System.out.println(time.format(formatter));
//	  }
//	 }
	
//	public static void main(String[] args) {
//        // Set the specific day
//        LocalDate date = LocalDate.of(2024, 3, 3); // Year, Month, Day
//
//        // Define the format for printing the time
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        // Loop through the 24 hours of the day
//        for (int hour = 0; hour < 24; hour++) {
//            // Create a LocalDateTime object for each hour of the day
//            LocalDateTime time = LocalDateTime.of(date, LocalTime.of(hour, 0));
//
//            // Print the formatted time
//            System.out.println(time.format(formatter));
//        }
//    }
	
//	 public static void main(String[] args) {
////	        // Set the specific day
////	        LocalDate date = LocalDate.of(2023, 10, 11); // Year, Month, Day
////
////	        // Define the format for printing the time
////	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
////
////	        // Loop through the 24 hours of the day
////	        for (int hour = 0; hour < 24; hour++) {
////	            // Create a LocalDateTime object for each hour of the day
////	            LocalDateTime time = LocalDateTime.of(date, LocalTime.of(hour, 0));
////	           // String format = time.format(formatter);
////	            // Print the formatted time
////	            System.out.println(time.format(formatter));
////	        }
//		 
//		 // Define the specific date
//		 String dateTimeString = "2024-03-04 10:56:10";
//
//	        // Define the format for parsing the date-time string
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	        // Parse the date-time string into a LocalDateTime object
//	        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
//
//	        // Get the previous day
//	        LocalDate previousDay = dateTime.toLocalDate().minusDays(1);
//System.out.println(previousDay.toString());
//	        // Print the formatted previous day
//	        System.out.println(previousDay.format(formatter));
//	    }
	
//		public static void main(String[] args) throws Exception {
//			//twentyFourHrsLoopForPericularDate("");
//			  // Define the date-time string
//	        String dateTimeString = "2024-03-04 10:56:10";
//
//	        // Define the format for parsing the date-time string
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	        // Parse the date-time string into a LocalDateTime object
//	        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
//
//	        // Create a list to store the formatted times
//	        List<String> listOfTime = new ArrayList<>();
//
//	        // Define the format for printing the time
//	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
//
//	        // Loop through the 24 hours of the day
//	        for (int hour = 0; hour < 24; hour++) {
//	            // Create a LocalDateTime object for each hour of the day
//	            LocalDateTime time = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(hour, 0));
//	            listOfTime.add(time.format(formatter2));
//	        }
//
//	       
//	    System.out.println(listOfTime);
//			
//		}
	
	public static int f(int n) {
		
		if(n>=5) {
			return 1;
		}
		return f(n+1)+f(n+2);
	}
		
		public static void main(String[] args) {
			   
			f = f(1);
			System.out.println(f);
	      
	    }
		
}
