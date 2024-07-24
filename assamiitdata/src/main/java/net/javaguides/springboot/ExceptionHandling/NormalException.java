package net.javaguides.springboot.ExceptionHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exc")
public class NormalException {
	
	@Autowired
	NormalExceptionService service;
	
	
	// If Exception Occuers it well stop the program to run sequence we need to keep trycatch
	// try  this program throws well throw the exception and it well catch
	@GetMapping("/method")
	public void exception() {
	   int a=1;
	   int b=0;
	   try {
           Class.forName("GeeksForGeeks");

		   
		   int arr[] = null; // array is assigned a null value  
	       System.out.println("The length of the array arr is: " + arr.length);  
	        
		   int c=a/b;
		   System.out.println(c+"   C VALUE");
	   }
	   catch(ArithmeticException w) {
           System.out.println("In EXCPTIONhANDLED");		   
	   }
	   catch(NullPointerException w) {
           System.out.println("In NullPointerException");		   
	   } 
	   catch (ClassNotFoundException e) {
		    e.printStackTrace();
	   }finally {
	       System.out.println("Remove All the methods");	
     	}
 	   System.out.println("gOGING aFTER");
	   service.mainServics("ss");
	}
	
	// Throw Uses : Throw use only when u want to get a exception 
	// If u want to throw exception u need to use throw, Then it well not movie forward 
	@RequestMapping("/exceptthrows")
	public void mainException() {
		
		int a=10;
		if(a==10) {
			throw new ArithmeticException("s");
		}
	 	System.out.println("Pop");
	}
	
	//How to get a throws Exception
	//Note Main Should be Sourended with trycatch
	@RequestMapping("/throwsException")
	public void mainExceptionUseingThrows() throws ArithmeticException, NullPointerException {
		System.out.println("In Servisess");
		try {
			service.putThrowsException("");
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
