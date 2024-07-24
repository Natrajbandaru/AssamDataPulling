package net.javaguides.springboot.ExceptionHandling;

import org.springframework.stereotype.Service;

@Service
public class NormalExceptionService2 {
	
	public void putThrowsException(String string) throws ArithmeticException , NullPointerException, ClassNotFoundException {
		System.out.println("In Excep");
		Class.forName("ssss");
		System.out.println("ppop");
		System.out.println(1/0);
		System.out.println("wew");

	}

	
}
