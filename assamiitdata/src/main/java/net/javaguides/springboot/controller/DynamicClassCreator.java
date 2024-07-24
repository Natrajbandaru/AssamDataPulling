package net.javaguides.springboot.controller;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class DynamicClassCreator {
//
//	public static void main(String[] args) {
//	    // Define class name and attributes
//	    String className = "Person";
//	    String attributeName = "name";
//	    Class<?> attributeType = String.class;
//
//	    // Create the class
//	    Class<?> dynamicClass = createDynamicClass(className);
//
//	    // Check if the dynamicClass is not null
//	    if (dynamicClass != null) {
//	        // Add annotations to the attribute
//	        addAnnotationsToField(dynamicClass, attributeName, attributeType);
//
//	        // Print the class details
//	        System.out.println("Attributes: ");
//	        for (Field field : dynamicClass.getDeclaredFields()) {
//	            System.out.println(field.getName() + " - Type: " + field.getType().getSimpleName());
//	            Annotation[] annotations = field.getDeclaredAnnotations();
//	            for (Annotation annotation : annotations) {
//	                System.out.println("  Annotation: " + annotation.annotationType().getSimpleName());
//	            }
//	        }
//	    } else {
//	        System.out.println("Failed to create the dynamic class.");
//	    }
//	}
//
//	private static Class<?> createDynamicClass(String className) {
//	    ClassLoader classLoader = DynamicClassCreator.class.getClassLoader();
//	    try {
//	        return Class.forName(className, false, classLoader);
//	    } catch (ClassNotFoundException e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}
//
//
//
//    private static void addAnnotationsToField(Class<?> clazz, String fieldName, Class<?> fieldType) {
//        try {
//            if (clazz != null) {
//                Field field = clazz.getDeclaredField(fieldName);
//
//                // Example: Add JPA annotations
//                field.setAccessible(true);
//
//                if (field.getType() != null) {
//                    try {
//                        Object fieldValue = field.getType().getDeclaredConstructor().newInstance();
//                        field.set(clazz, fieldValue);
//                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    // Handle the case where field.getType() is null
//                }
//
//                // Rest of the code...
//            } else {
//                // Handle the case where clazz is null
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }
//
	  public static List<String> getDateRange(String startDateStr, String endDateStr) {
	        List<String> dateRange = new ArrayList<>();
	        
	        LocalDate startDate = LocalDate.parse(startDateStr);
	        LocalDate endDate = LocalDate.parse(endDateStr);
	        
	        while (!startDate.isAfter(endDate)) {
	            dateRange.add(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            startDate = startDate.plusDays(1);
	        }
	        
	        return dateRange;
	    }
	  
	    public static void main(String[] args) {
		  String startDateStr = "2023-03-01"; // Start date
	        String endDateStr = "2024-03-10"; // End date
		  List<String> dateRange = getDateRange(startDateStr,endDateStr);
		  System.out.println(dateRange);
	  }
}
